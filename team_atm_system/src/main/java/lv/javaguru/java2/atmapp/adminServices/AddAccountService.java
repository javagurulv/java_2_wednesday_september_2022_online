package lv.javaguru.java2.atmapp.adminServices;


import lv.javaguru.java2.atmapp.Accounts;
import lv.javaguru.java2.atmapp.database.Database;


public class AddAccountService {

    private Database database;

    public AddAccountService(Database database) {
        this.database = database;
    }

    public void execute(String name, int userID) {
        Accounts accounts = new Accounts(name, userID, 0);
        database.addAccount(accounts);
    }
}
