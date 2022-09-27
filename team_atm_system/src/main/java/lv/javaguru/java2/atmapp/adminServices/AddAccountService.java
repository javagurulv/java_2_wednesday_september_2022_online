package lv.javaguru.java2.atmapp.adminServices;


import lv.javaguru.java2.atmapp.Accounts;
import lv.javaguru.java2.atmapp.database.Database;
import lv.javaguru.java2.atmapp.requests.adminRequests.AddAccountRequest;
import lv.javaguru.java2.atmapp.responses.adminResponses.AddAccountResponse;


public class AddAccountService {

    private Database database;

    public AddAccountService(Database database) {
        this.database = database;
    }

   /* public void execute(String name, int userID) {
        Accounts accounts = new Accounts(name, userID, 0);
        database.addAccount(accounts);
    }
    */

    public AddAccountResponse execute(AddAccountRequest request) {
        Accounts accounts = new Accounts(request.getName(), request.getUserID());
        database.addAccount(accounts);
        return new AddAccountResponse(accounts);
    }
}
