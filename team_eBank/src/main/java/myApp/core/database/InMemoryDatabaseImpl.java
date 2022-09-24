package myApp.core.database;

import myApp.Account;
import myApp.BankAccount;
import myApp.Roles;

import java.util.*;

public class InMemoryDatabaseImpl implements DataBase {
    private final Map<String, BankAccount> bankAccountMap = new HashMap<>();

    public InMemoryDatabaseImpl() {

        BankAccount bankAccount2 = new BankAccount("Vlad", "Kulikov", Roles.Regular_user, "111-317");
        BankAccount bankAccount3 = new BankAccount("Alex", "Ivanov", Roles.Regular_user, "111-111");
        bankAccount3.setAccounts(new Account(2L, 1000));
        BankAccount adminAccount = new BankAccount("Admin", "Admin", Roles.Admin, "Admin");
        bankAccountMap.put(adminAccount.getPersonalCode(), adminAccount);
        addBankAccount(bankAccount2);
        addBankAccount(bankAccount3);

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
    public Map<String , BankAccount> getAllBankAccountsMap() {
        return bankAccountMap;
    }

    @Override
    public BankAccount seeYourAccount(String personalCode) {
        return bankAccountMap.get(personalCode);
    }

    //Rewrite method
    @Override
    public boolean bankTransfer(String personalCode, String anotherPersonalCode, int value) {
        Account account = bankAccountMap.get(personalCode).getAccounts();
        bankAccountMap.get(personalCode).getAccounts().setBalance(account.getBalance() - value);

        Account accountAnother = bankAccountMap.get(anotherPersonalCode).getAccounts();
        bankAccountMap.get(anotherPersonalCode).getAccounts().setBalance(accountAnother.getBalance() + value);
        return true;
    }

    @Override
    public String logIn(String personalCode) {
        return personalCode;
    }



    @Override
    public BankAccount openAccount(String personalCode) {
        if (bankAccountMap.get(personalCode).getAccounts() == null) {
            bankAccountMap.get(personalCode).setAccounts(new Account(idForAccount, 1000));
        }
        return bankAccountMap.get(personalCode);
    }

    @Override
    public boolean closeAccount(String personalCode) {
        if (bankAccountMap.get(personalCode).getAccounts() != null &&
                bankAccountMap.get(personalCode).getAccounts().getBalance() == 0) {
            bankAccountMap.get(personalCode).setAccounts(null);
            return true;
        }
        return false;
    }

}
