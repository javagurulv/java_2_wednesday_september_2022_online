package generalPackage.balanceOperations;

import generalPackage.database.Database;

public class IncreaseBalance {

    private Database database;

    public IncreaseBalance(Database database) {
        this.database = database;
    }


    public void execute(int userID, int amountToIncrease) {
        database.increaseBalance(userID, amountToIncrease);
    }

//    public IncreaseBalanceResponse execute (IncreaseBalanceRequest request){
//        boolean balanceChanged = false;
//        return new IncreaseBalanceResponse(balanceChanged);
//    }
}

