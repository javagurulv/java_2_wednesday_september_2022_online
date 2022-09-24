package atmApplication.BalanceOperations;

import atmApplication.Database.Database;

public class PrintBalance implements BalanceOperations {

    private int userID;

    public PrintBalance(int userID) {
        this.userID = userID;
    }

    @Override
    public void execute(Database database) {
        database.printBalance(userID);
    }
}