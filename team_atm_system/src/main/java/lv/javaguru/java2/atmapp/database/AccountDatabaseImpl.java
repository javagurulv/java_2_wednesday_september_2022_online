package lv.javaguru.java2.atmapp.database;

import lv.javaguru.java2.atmapp.Accounts;

import java.util.ArrayList;
import java.util.List;

public class AccountDatabaseImpl implements Database {

    private List<Accounts> accounts = new ArrayList<>();
    private int userID;

    public AccountDatabaseImpl() {
        accounts.add(new Accounts("Ivan", 1234, 300));
        accounts.add(new Accounts("Boris", 2345, 300));
        accounts.add(new Accounts("Phedor", 4567, 0));
    }

    @Override
    //   public void addAccount(Accounts account) {
    public void addAccount(Accounts account) {
        accounts.add(account);
    }

    @Override
    public void deleteAccount(int userID) {
        accounts.stream()
                .filter(account -> account.getUserID() == userID)
                .findFirst()
                .ifPresent(account -> accounts.remove(account));
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return accounts;
    }


    //    added:
    @Override
    public void increaseBalance(int userID, int amount) {
        if (userID != 0 && amount > 0) {
            accounts.stream()
                    .filter(account -> account.getUserID() == userID)
                    .forEach(account -> account.setBalance(account.getBalance() + amount));
        }
    }

    @Override
    public void decreaseBalance(int userID, int amount) {
        if (userID != 0 && amount > 0) {
            accounts.stream()
                    .filter(account -> account.getUserID() == userID)
                    .forEach(accounts -> accounts.setBalance(accounts.getBalance() - amount));
        }
    }

    @Override
    public void printBalance(int userID) {
        accounts.stream()
                .filter(account -> account.getUserID() == userID)
                .forEach(account -> System.out.println("Current balance is: " + account.getBalance()));
    }

    @Override
    public boolean isExist(String name) {
        return accounts.contains(name);
    }

    @Override
    public void findUserByID(int userID) {
        for (Accounts account : accounts){
            if (account.getUserID() == userID){
                System.out.println("User found:" + account);
            }
        }
    }


}
