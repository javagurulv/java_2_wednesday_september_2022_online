package generalPackage.core.services.adminOperations;

import generalPackage.Accounts;
import generalPackage.core.database.Database;
import generalPackage.core.requests.adminRequests.FindUserByIDRequest;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.responses.adminResponses.FindByIDAccountResponse;
import generalPackage.core.services.adminOperations.adminValidators.FindUserByIDServiceValidator;
import generalPackage.dependencyInjection.DIComponent;
import generalPackage.dependencyInjection.DIDependency;

import java.util.List;

@DIComponent
public class FindUserByIDService {

    @DIDependency
    private Database database;
    @DIDependency private FindUserByIDServiceValidator validator;

//    public FindUserByIDService(Database database, FindUserByIDServiceValidator validator) {
//        this.database = database;
//        this.validator = validator;
//    }

    public FindByIDAccountResponse execute(FindUserByIDRequest request) {
        List <CoreError> errors =validator.validate(request);
        if (!errors.isEmpty()){
            return new FindByIDAccountResponse(errors);
        }
        Accounts accountToFind = database.findUserByID(request.getUserIDtoFind());
        return new FindByIDAccountResponse(accountToFind);

    }
}
