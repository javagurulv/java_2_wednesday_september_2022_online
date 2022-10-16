package lv.javaguru.java2.atmapp.balanceServices;

import lv.javaguru.java2.atmapp.balanceServicesUI.Balance;
import lv.javaguru.java2.atmapp.database.Database;
import lv.javaguru.java2.atmapp.requests.adminRequests.AddAccountRequest;
import lv.javaguru.java2.atmapp.responses.userResponses.IncreaseBalanceResponse;

public class IncreaseBalance {

    //    Accounts accounts;
    private Database database;

    public IncreaseBalance(Database database) {
        this.database = database;
    }

//public void increaseBalance (int userID, int amount){
// accounts.stream()
//         .filter(account -> account.getUserID() == userID)
//         .findFirst()
//         .ifPresent(account -> account.setBalance(account.getBalance() + amount) );
//}

    public void execute(int userID, int amountToIncrease) {
        database.increaseBalance(userID, amountToIncrease);
    }


}

