package lv.javaguru.java2.atmapp.core.services.adminOperations;

import lv.javaguru.java2.atmapp.Accounts;
import lv.javaguru.java2.atmapp.core.database.Database;
import lv.javaguru.java2.atmapp.core.requests.adminRequests.FindUserByIDRequest;
import lv.javaguru.java2.atmapp.core.responses.adminResponses.CoreError;
import lv.javaguru.java2.atmapp.core.responses.adminResponses.FindByIDAccountResponse;
import lv.javaguru.java2.atmapp.core.services.adminOperations.adminValidators.FindUserByIDServiceValidator;

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
