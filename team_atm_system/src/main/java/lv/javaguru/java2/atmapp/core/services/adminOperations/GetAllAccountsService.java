package lv.javaguru.java2.atmapp.core.services.adminOperations;


import lv.javaguru.java2.atmapp.Accounts;
import lv.javaguru.java2.atmapp.core.database.Database;
import lv.javaguru.java2.atmapp.core.requests.adminRequests.GetAllAccountsRequest;
import lv.javaguru.java2.atmapp.core.responses.adminResponses.GetAllAccountsResponse;

import java.util.List;

public class GetAllAccountsService {

    private Database database;

    public GetAllAccountsService(Database database) {
        this.database = database;
    }

    public GetAllAccountsResponse execute (GetAllAccountsRequest request){
        List <Accounts> accounts = database.getAllAccounts();
        return new GetAllAccountsResponse(accounts);
    }
}
