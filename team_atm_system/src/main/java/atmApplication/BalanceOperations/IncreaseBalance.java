package atmApplication.BalanceOperations;

import atmApplication.Database.Database;

public class IncreaseBalance implements BalanceOperations {

    private int amount;
    private int userID;

    public IncreaseBalance(int userID, int amount) {
        this.amount = amount;
        this.userID = userID;
    }

    @Override
    public void execute(Database database) {
        database.increaseBalance(userID, amount);
    }
}

