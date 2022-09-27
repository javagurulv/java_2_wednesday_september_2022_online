package lv.javaguru.java2.atmapp.balanceServices;

import lv.javaguru.java2.atmapp.database.Database;

public class PrintBalance  {

    private Database database;

    public PrintBalance(Database database) {
        this.database = database;
    }

    public void execute(int userID) {
        database.printBalance(userID);
    }
}
