package lv.javaguru.java2.atmapp.adminServices;

import lv.javaguru.java2.atmapp.database.Database;
import lv.javaguru.java2.atmapp.requests.adminRequests.DeleteAccountRequest;
import lv.javaguru.java2.atmapp.responses.adminResponses.DeleteAccountResponse;

public class DeleteAccountService {

    private Database database;

    public DeleteAccountService(Database database) {
        this.database = database;
    }

 /*   public void execute(int userID) {
        database.deleteAccount(userID);
    }

  */

    public DeleteAccountResponse execute(DeleteAccountRequest request) {
        boolean isAccountDeleted = database.deleteAccount(request.getAccountToDelete());
        return new DeleteAccountResponse(isAccountDeleted);
    }
}
