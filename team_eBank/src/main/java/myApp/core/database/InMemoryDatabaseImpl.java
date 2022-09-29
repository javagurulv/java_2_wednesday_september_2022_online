package myApp.core.database;

import myApp.core.domain.Account;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;

import java.util.*;

public class InMemoryDatabaseImpl implements DataBase {
    private final List<BankAccount> bankAccounts = new ArrayList<>() ;


    public InMemoryDatabaseImpl() {
        BankAccount bankAccount = new BankAccount("Vlad", "Kulikov","password", Roles.Regular_user, "111-317");
        BankAccount adminAccount = new BankAccount("Admin", "Admin","Admin", Roles.Admin, "01");
        bankAccounts.add(adminAccount);
        addBankAccount(bankAccount);
    }

    private Long id = 1L;
    private Long idForAccount = 1L;

    @Override
    public void addBankAccount(BankAccount bankAccount) {
        bankAccount.setId(id);
        id++;
        bankAccounts.add(bankAccount);
    }

    @Override
    public boolean deleteBankAccount(String personalCode) {
        return bankAccounts.removeIf(bankAccount -> bankAccount.getPersonalCode().equals(personalCode));

    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return bankAccounts;
    }

    @Override
    public Optional<BankAccount> seeYourAccount(String personalCode) {
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .findFirst();
    }

    @Override
    public boolean bankTransfer(String personalCode, String anotherPersonalCode, int value) {
        int checkBalance = bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .findFirst().get().getAccounts().getBalance();
        bankAccounts.stream().filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .forEach(bankAccount -> bankAccount.getAccounts()
                        .setBalance(bankAccount.getAccounts().getBalance() - value));
        bankAccounts.stream().filter(bankAccount -> bankAccount.getPersonalCode().equals(anotherPersonalCode))
                .forEach(bankAccount -> bankAccount.getAccounts()
                        .setBalance(bankAccount.getAccounts().getBalance() + value));
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .anyMatch(bankAccount -> bankAccount.getAccounts().getBalance() != checkBalance);
    }

    @Override
    public boolean openAccount(String personalCode) {
        bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .filter(bankAccount -> bankAccount.getAccounts() == null)
                .forEach(bankAccount -> bankAccount.setAccount(new Account(idForAccount, 500)));
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .anyMatch(bankAccount -> bankAccount.getAccounts() != null);
    }

    @Override
    public boolean closeAccount(String personalCode) {
        bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .filter(bankAccount -> bankAccount.getAccounts() != null)
                .filter(bankAccount -> bankAccount.getAccounts().getBalance() == 0)
                .forEach(bankAccount -> bankAccount.setAccount(null));
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .anyMatch(bankAccount -> bankAccount.getAccounts() == null);
    }

}
