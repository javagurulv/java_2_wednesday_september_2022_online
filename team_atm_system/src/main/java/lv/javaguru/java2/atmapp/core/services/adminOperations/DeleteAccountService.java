package lv.javaguru.java2.atmapp.core.services.adminOperations;

import lv.javaguru.java2.atmapp.core.database.Database;
import lv.javaguru.java2.atmapp.core.requests.adminRequests.DeleteAccountRequest;
import lv.javaguru.java2.atmapp.core.responses.adminResponses.CoreError;
import lv.javaguru.java2.atmapp.core.responses.adminResponses.DeleteAccountResponse;
import lv.javaguru.java2.atmapp.core.services.adminOperations.adminValidators.DeleteAccountServiceValidator;

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
