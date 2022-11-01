package lv.javaguru.java2.atmapp.core.services.adminOperations;


import lv.javaguru.java2.atmapp.Accounts;
import lv.javaguru.java2.atmapp.core.database.Database;
import lv.javaguru.java2.atmapp.core.requests.adminRequests.AddAccountRequest;
import lv.javaguru.java2.atmapp.core.responses.adminResponses.AddAccountResponse;
import lv.javaguru.java2.atmapp.core.responses.adminResponses.CoreError;
import lv.javaguru.java2.atmapp.core.services.adminOperations.adminValidators.AddAccountServiceValidator;

import java.util.List;


public class AddAccountService {

    private Database database;
    private AddAccountServiceValidator validator;

    public AddAccountService(Database database, AddAccountServiceValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddAccountResponse execute(AddAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddAccountResponse(errors);
        }
        Accounts account = new Accounts(request.getUserName(), request.getUserId(), 0);
        database.addAccount(account);
        return new AddAccountResponse(account);
    }
}
