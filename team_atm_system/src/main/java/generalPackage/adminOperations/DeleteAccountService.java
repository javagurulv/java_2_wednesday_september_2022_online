package generalPackage.adminOperations;

import generalPackage.adminRequests.DeleteAccountRequest;
import generalPackage.adminResponses.DeleteAccountResponse;
import generalPackage.database.Database;

public class DeleteAccountService {

    private Database database;

    public DeleteAccountService(Database database) {
        this.database = database;
    }

    public DeleteAccountResponse execute(DeleteAccountRequest request) {
        boolean accountDeleted = database.deleteAccount(request.getUserIDtoDelete());
        return new DeleteAccountResponse(accountDeleted);
    }
}
