package generalPackage.adminOperations;

import generalPackage.Accounts;
import generalPackage.adminRequests.FindUserByIDRequest;
import generalPackage.adminResponses.CoreError;
import generalPackage.adminResponses.FindByIDAccountResponse;
import generalPackage.database.Database;

import java.util.List;

public class FindUserByIDService {

    private Database database;
    private FindUserByIDServiceValidator validator;

    public FindUserByIDService(Database database, FindUserByIDServiceValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public FindByIDAccountResponse execute(FindUserByIDRequest request) {
        List <CoreError> errors =validator.validate(request);
        if (!errors.isEmpty()){
            return new FindByIDAccountResponse(errors);
        }
        Accounts accountToFind = database.findUserByID(request.getUserIDtoFind());
        return new FindByIDAccountResponse(accountToFind);

    }
}
