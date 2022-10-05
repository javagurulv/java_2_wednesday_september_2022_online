package generalPackage.database;

import generalPackage.Accounts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDatabaseImpl implements Database {

    private List<Accounts> accounts = new ArrayList<>();
    private int userID;

    public AccountDatabaseImpl() {
        accounts.add(new Accounts("Ivan", 1234, 300));
        accounts.add(new Accounts("Boris", 2345, 300));
        accounts.add(new Accounts("Phedor", 4567, 0));
    }

    @Override
    public void addAccount(Accounts account) {
        accounts.add(account);
    }

    @Override
    public boolean deleteAccount(int userID) {
        boolean isAccountDeleted = false;
        Optional<Accounts> accountToDeleteOptional = accounts.stream()
                .filter(account -> account.getUserID() == userID)
                .findFirst();
        if (accountToDeleteOptional.isPresent()) {
            Accounts accountToDelete = accountToDeleteOptional.get();
            isAccountDeleted = accounts.remove(accountToDelete);
        }
        return isAccountDeleted;
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return accounts;
    }


    //    added:
    @Override
    public boolean increaseBalance(int userID, int amount) {
        if (userID != 0 && amount > 0) {
            accounts.stream()
                    .filter(account -> account.getUserID() == userID)
                    .forEach(account -> account.setBalance(account.getBalance() + amount));
        }
        return false;
    }

//    @Override
//    public boolean increaseBalance(int userID, int amount) {
//        int begin = 0;
//        accounts.stream()
//                .filter(account -> account.getUserID() == userID)
//                .forEach(account -> begin == account.getBalance());
//        if (userID != 0 && amount > 0) {
//            accounts.stream()
//                    .filter(account -> account.getUserID() == userID)
//                    .forEach(account -> account.setBalance(account.getBalance() + amount));
//        }
//        return (accounts.get(userID).getBalance() = begin + amount);
//    }

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
    public Accounts findUserByID(int userID) {
        Accounts accountToFind = accounts.stream()
                .filter(account -> userID == account.getUserID())
                .findAny()
                .orElse(null);
        return accountToFind;
    }

}