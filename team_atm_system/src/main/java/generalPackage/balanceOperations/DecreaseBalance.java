package generalPackage.balanceOperations;

import generalPackage.database.Database;

public class DecreaseBalance  {

    private Database database;

    public DecreaseBalance(Database database) {
        this.database = database;
    }


    public void execute(int userID, int amountToDecrease) {
        database.decreaseBalance(userID, amountToDecrease);
    }
}
