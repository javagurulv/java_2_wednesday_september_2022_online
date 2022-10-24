package generalPackage.core.services.adminOperations;


import generalPackage.Accounts;
import generalPackage.core.database.Database;
import generalPackage.core.requests.adminRequests.AddAccountRequest;
import generalPackage.core.responses.adminResponses.AddAccountResponse;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.services.adminOperations.adminValidators.AddAccountServiceValidator;

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
