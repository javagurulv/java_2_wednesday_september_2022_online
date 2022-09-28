package generalPackage.database;

import generalPackage.Accounts;

import java.util.List;

public interface Database {

    void addAccount(Accounts accounts);

    boolean deleteAccount(int userID);

    List<Accounts> getAllAccounts();

    //    added:
    void increaseBalance(int userID, int amount);

    void decreaseBalance(int userID, int amount);

    void printBalance(int userID);

    boolean isExist(String name);

    // added 23-09:
    Accounts findUserByID(int userID);

}
