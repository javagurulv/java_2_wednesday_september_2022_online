package lv.javaguru.java2.atmapp.adminOperations;

import lv.javaguru.java2.atmapp.database.Database;

public class DeleteAccountService {

    private Database database;

    public DeleteAccountService(Database database) {
        this.database = database;
    }

    public void execute(int userID) {
        database.deleteAccount(userID);
    }
}
