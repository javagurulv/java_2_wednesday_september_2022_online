package myApp.core.database;

import myApp.core.domain.Account;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;

import java.util.*;

public class InMemoryDatabaseImpl implements DataBase {
    private final Map<String, BankAccount> bankAccountMap = new HashMap<>();

    public InMemoryDatabaseImpl() {
        BankAccount bankAccount = new BankAccount("Vlad", "Kulikov", Roles.Regular_user, "111-317");
        BankAccount adminAccount = new BankAccount("Admin", "Admin", Roles.Admin, "01");
        bankAccountMap.put(adminAccount.getPersonalCode(), adminAccount);
        addBankAccount(bankAccount);
    }

    private Long id = 1L;
    private Long idForAccount = 1L;

    @Override
    public void addBankAccount(BankAccount bankAccount) {
        bankAccount.setId(id);
        id++;
        bankAccountMap.put(bankAccount.getPersonalCode(), bankAccount);
    }

    @Override
    public boolean deleteBankAccount(String personalCode) {
        bankAccountMap.remove(personalCode);
        return !bankAccountMap.containsKey(personalCode);
    }

    @Override
    public Map<String, BankAccount> getAllBankAccountsMap() {
        return bankAccountMap;
    }

    @Override
    public BankAccount seeYourAccount(String personalCode) {
        return bankAccountMap.get(personalCode);
    }

    @Override
    public boolean bankTransfer(String personalCode, String anotherPersonalCode, int value) {
        Account account = bankAccountMap.get(personalCode).getAccounts();
        int checkBalance = account.getBalance();
        bankAccountMap.get(personalCode).getAccounts().setBalance(account.getBalance() - value);

        Account accountAnother = bankAccountMap.get(anotherPersonalCode).getAccounts();
        bankAccountMap.get(anotherPersonalCode).getAccounts().setBalance(accountAnother.getBalance() + value);

        return bankAccountMap.get(personalCode).getAccounts().getBalance() != checkBalance;

    }

    @Override
    public boolean openAccount(String personalCode) {
        if (bankAccountMap.containsKey(personalCode)) {
            if (bankAccountMap.get(personalCode).getAccounts() == null) {
                bankAccountMap.get(personalCode).setAccount(new Account(idForAccount, 500));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean closeAccount(String personalCode) {
        if (bankAccountMap.get(personalCode).getAccounts() != null &&
                bankAccountMap.get(personalCode).getAccounts().getBalance() == 0) {
            bankAccountMap.get(personalCode).setAccount(null);
            return true;
        }
        return false;
    }

}
