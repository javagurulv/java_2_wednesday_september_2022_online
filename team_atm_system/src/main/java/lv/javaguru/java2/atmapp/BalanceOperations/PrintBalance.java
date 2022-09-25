package lv.javaguru.java2.atmapp.BalanceOperations;

import lv.javaguru.java2.atmapp.Database.Database;

public class PrintBalance  {

    private Database database;

    public PrintBalance(Database database) {
        this.database = database;
    }

    public void execute(int userID) {
        database.printBalance(userID);
    }
}
