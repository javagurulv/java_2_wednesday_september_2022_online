package generalPackage.core.services.adminOperations;

import generalPackage.core.database.Database;
import generalPackage.core.requests.adminRequests.DeleteAccountRequest;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.responses.adminResponses.DeleteAccountResponse;
import generalPackage.core.services.adminOperations.adminValidators.DeleteAccountServiceValidator;
import generalPackage.dependencyInjection.DIComponent;
import generalPackage.dependencyInjection.DIDependency;

import java.util.List;


@DIComponent
public class DeleteAccountService {

    @DIDependency
    private Database database;
    @DIDependency
    private DeleteAccountServiceValidator validator;


//    public DeleteAccountService(Database database, DeleteAccountServiceValidator validator) {
//        this.database = database;
//        this.validator = validator;
//    }

    public DeleteAccountResponse execute(DeleteAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteAccountResponse(errors);
        }
        boolean accountDeleted = database.deleteAccount(request.getUserIDtoDelete());
        return new DeleteAccountResponse(accountDeleted);
    }
}
