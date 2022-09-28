package generalPackage.adminOperations;


import generalPackage.Accounts;
import generalPackage.adminRequests.AddAccountRequest;
import generalPackage.adminResponses.AddAccountResponse;
import generalPackage.database.Database;


public class AddAccountService {

    private Database database;

    public AddAccountService(Database database) {
        this.database = database;
    }

    public AddAccountResponse execute(AddAccountRequest request) {
        Accounts account = new Accounts(request.getUserName(), request.getUserId(), 0);
        database.addAccount(account);
        return new AddAccountResponse(account);
    }
}
