package generalPackage.balanceOperations;

import generalPackage.database.Database;

public class PrintBalance  {

    private Database database;

    public PrintBalance(Database database) {
        this.database = database;
    }

    public void execute(int userID) {
        database.printBalance(userID);
    }
}
