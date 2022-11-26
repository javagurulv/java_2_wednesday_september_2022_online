package myApp.core.database;


import myApp.core.domain.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class InMemoryDatabaseImpl {
    private final List<BankAccount> bankAccounts = new ArrayList<>();

    private Long id = 1L;
    private Long idForAccount = 1L;

   // @Override
    public void addBankAccount(BankAccount bankAccount) {
       boolean result = bankAccounts.stream()
                                .anyMatch(bankAccount1 -> !bankAccount1.getPersonalCode().equals(bankAccount.getPersonalCode()));
       if (result) {
           bankAccount.setId(id);
           id++;
           bankAccounts.add(bankAccount);
       }
    }

  //  @Override
    public boolean deleteBankAccount(String personalCode) {
        return bankAccounts.removeIf(bankAccount -> bankAccount.getPersonalCode().equals(personalCode));

    }

    //@Override
    public List<BankAccount> getAllBankAccounts() {
        return bankAccounts;
    }

  //  @Override
    public Optional<BankAccount> seeYourAccount(String personalCode) {
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .findFirst();
    }
    /*
    @Override
    public boolean bankTransfer(String personalCode, String anotherPersonalCode, int value) {
        int checkBalance = bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .findFirst().get().getAccount().getBalance();
        bankAccounts.stream().filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .forEach(bankAccount -> bankAccount.getAccount()
                        .setBalance(bankAccount.getAccount().getBalance() - value));
        bankAccounts.stream().filter(bankAccount -> bankAccount.getPersonalCode().equals(anotherPersonalCode))
                .forEach(bankAccount -> bankAccount.getAccount()
                        .setBalance(bankAccount.getAccount().getBalance() + value));
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .anyMatch(bankAccount -> bankAccount.getAccount().getBalance() != checkBalance);
    }



    @Override
    public boolean openAccount(String personalCode) {
        bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .filter(bankAccount -> bankAccount.getAccount() == null)
                .forEach(bankAccount -> bankAccount.setAccount(new Account(idForAccount, 500)));
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .anyMatch(bankAccount -> bankAccount.getAccount() != null);
    }

    @Override
    public boolean closeAccount(String personalCode) {
        bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .filter(bankAccount -> bankAccount.getAccount() != null)
                .filter(bankAccount -> bankAccount.getAccount().getBalance() == 0)
                .forEach(bankAccount -> bankAccount.setAccount(null));
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .anyMatch(bankAccount -> bankAccount.getAccount() == null);
    }
*/
   // @Override
    public List<BankAccount> findByName(String name) {
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getName().equals(name))
                .collect(Collectors.toList());
    }

   // @Override
    public List<BankAccount> findBySurname(String surname) {
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    //@Override
    public List<BankAccount> findByPersonalCode(String personalCode) {
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

   // @Override
    public List<BankAccount> findByNameAndSurname(String name, String surname) {
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getName().equals(name))
                .filter(bankAccount -> bankAccount.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

   // @Override
    public List<BankAccount> findByNameAndPersonalCode(String name, String personalCode) {
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getName().equals(name))
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

  //  @Override
    public List<BankAccount> findBySurnameAndPersonalCode(String surname, String personalCode) {
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getSurname().equals(surname))
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

  //  @Override
    public List<BankAccount> findByNameAndSurnameAndPersonalCode(String name, String surname, String personalCode) {
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getName().equals(name))
                .filter(bankAccount -> bankAccount.getSurname().equals(surname))
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }
}
