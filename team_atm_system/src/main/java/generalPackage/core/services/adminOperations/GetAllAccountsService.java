package generalPackage.core.services.adminOperations;


import generalPackage.Accounts;
import generalPackage.core.database.Database;
import generalPackage.core.requests.adminRequests.GetAllAccountsRequest;
import generalPackage.core.responses.adminResponses.GetAllAccountsResponse;

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
