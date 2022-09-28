package generalPackage.adminOperations;

import generalPackage.Accounts;
import generalPackage.adminRequests.FindUserByIDRequest;
import generalPackage.adminResponses.FindByIDAccountResponse;
import generalPackage.database.Database;


public class FindUserByIDServise {

    private Database database;

    public FindUserByIDServise(Database database) {
        this.database = database;
    }

    public FindByIDAccountResponse execute(FindUserByIDRequest request) {
//    Accounts accountToFind = new Accounts (request.getUserIDtoFind());
        Accounts accountToFind = database.findUserByID(request.getUserIDtoFind());
//        return new FindByIDAccountResponse(accountToFind);
        return new FindByIDAccountResponse(accountToFind);

    }
}
