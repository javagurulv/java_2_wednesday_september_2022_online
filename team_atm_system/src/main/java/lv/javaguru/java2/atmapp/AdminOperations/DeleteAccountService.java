package lv.javaguru.java2.atmapp.AdminOperations;

import lv.javaguru.java2.atmapp.Database.Database;

public class DeleteAccountService {

    private Database database;

    public DeleteAccountService(Database database) {
        this.database = database;
    }

    public void execute(int userID) {
        database.deleteAccount(userID);
    }
}
