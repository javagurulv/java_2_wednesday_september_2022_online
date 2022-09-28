package generalPackage.adminOperations;


import generalPackage.Accounts;
import generalPackage.adminRequests.GetAllAccountsRequest;
import generalPackage.adminResponses.GetAllAccountsResponse;
import generalPackage.database.Database;

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
