package generalPackage.adminOperations;

import generalPackage.adminRequests.DeleteAccountRequest;
import generalPackage.adminResponses.CoreError;
import generalPackage.adminResponses.DeleteAccountResponse;
import generalPackage.database.Database;

import java.util.List;


public class DeleteAccountService {

    private Database database;
    private DeleteAccountServiceValidator validator;



    public DeleteAccountService(Database database, DeleteAccountServiceValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DeleteAccountResponse execute(DeleteAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return new DeleteAccountResponse(errors);
        }
        boolean accountDeleted = database.deleteAccount(request.getUserIDtoDelete());
        return new DeleteAccountResponse(accountDeleted);
    }
}
