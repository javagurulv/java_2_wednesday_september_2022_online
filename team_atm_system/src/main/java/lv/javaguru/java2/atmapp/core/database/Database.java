package lv.javaguru.java2.atmapp.core.database;

import lv.javaguru.java2.atmapp.Accounts;

import java.util.List;

public interface Database {

    void addAccount(Accounts accounts);

    boolean deleteAccount(int userID);

    List<Accounts> getAllAccounts();

    //    added:
    boolean increaseBalance(int userID, int amount);

    boolean decreaseBalance(int userID, int amount);

    int printBalance(int userID);

    boolean isExist(String name);

    boolean userIdIsExist(int userID);

    // added 23-09:
    Accounts findUserByID(int userID);

}
