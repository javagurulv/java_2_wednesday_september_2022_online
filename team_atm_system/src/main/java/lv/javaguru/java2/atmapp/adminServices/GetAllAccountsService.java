package lv.javaguru.java2.atmapp.adminServices;


import lv.javaguru.java2.atmapp.Accounts;
import lv.javaguru.java2.atmapp.database.Database;
import lv.javaguru.java2.atmapp.requests.adminRequests.GetAllAccountsRequest;
import lv.javaguru.java2.atmapp.responses.adminResponses.GetAllAccountsResponse;

import java.util.List;

public class GetAllAccountsService {

    private Database database;

    public GetAllAccountsService(Database database) {
        this.database = database;
    }

/*    public List<Accounts> execute (){
        return database.getAllAccounts();
    }

 */

    public GetAllAccountsResponse execute(GetAllAccountsRequest request) {
        List<Accounts> accounts = database.getAllAccounts();
        return new GetAllAccountsResponse(accounts);
    }
}
