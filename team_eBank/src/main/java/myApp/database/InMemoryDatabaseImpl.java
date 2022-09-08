package myApp.database;

import myApp.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabaseImpl implements DataBase {

    private List<BankAccount> bankAccounts = new ArrayList<>();
    private Long id = 1L;

    @Override
    public void addBankAccount(BankAccount bankAccount) {
       bankAccount.setId(id);
       id++;
       bankAccounts.add(bankAccount);
    }

    @Override
    public void deleteBankAccount(Long id) {
        bankAccounts.removeIf(bankAccount -> bankAccount.getId().equals(id));
    }


    @Override
    public List<BankAccount> getAllBankAccounts() {
        return bankAccounts;
    }

    @Override
    public void bankTransfer(Long userID, int value, Long anotherAccountID) {
        if(userID != 0 && anotherAccountID !=0 && value > 0) {
            bankAccounts.stream()
                    .filter(account -> isEqualsAccountsIDs(account, userID))
                    .forEach(account -> account.setBalance(account.getBalance() - value));
            bankAccounts.stream()
                    .filter(account -> isEqualsAccountsIDs(account, anotherAccountID))
                    .forEach(account -> account.setBalance(account.getBalance() + value));
        }
    }

    private boolean isEqualsAccountsIDs(BankAccount account,Long userID) {
        return account.getId().equals(userID) && userID != 0;
    }
}
