package generalPackage.core.database;

import generalPackage.Accounts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDatabaseImpl implements Database {

    private List<Accounts> accounts = new ArrayList<>();

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

        Accounts userAccount = accounts.stream()
                .filter(account -> account.getUserID() == userID)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User with ID not found" + userID));
        int begin = userAccount.getBalance();
        if (amount > 0) {
            userAccount.setBalance(userAccount.getBalance() + amount);
        }
        return userAccount.getBalance() == (begin + amount);
    }

    @Override
    public boolean decreaseBalance(int userID, int amount) {
        Accounts userAccount = accounts.stream()
                .filter(account -> account.getUserID() == userID)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User with ID was not found" + userID));
        int startingBalance = userAccount.getBalance();
        if (amount > 0 && startingBalance >= amount) {
            userAccount.setBalance(userAccount.getBalance() - amount);
        } else {
            System.out.println("Insufficient funds");
        }
        return userAccount.getBalance() == startingBalance - amount;
    }


    @Override
    public int printBalance(int userID) {
        Accounts userAccountToFind = accounts.stream()
                .filter(account -> userID == account.getUserID())
                .findAny()
                .orElse(null);
        return userAccountToFind.getBalance();
    }

    @Override
    public boolean isExist(String name) {
        return accounts.contains(name);
    }

    @Override
    public boolean userIdIsExist(int userID) {
        return accounts.contains(userID);
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