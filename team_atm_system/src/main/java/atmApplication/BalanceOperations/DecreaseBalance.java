package atmApplication.BalanceOperations;


import atmApplication.Database.Database;

public class DecreaseBalance implements BalanceOperations {

    private int amount;
    private int userID;

    public DecreaseBalance(int amount, int userID) {
        this.amount = amount;
        this.userID = userID;
    }

    @Override
    public void execute(Database database) {
        database.decreaseBalance(userID, amount);
    }
}