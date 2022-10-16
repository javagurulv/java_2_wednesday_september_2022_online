package generalPackage.adminOperations;


import generalPackage.Accounts;
import generalPackage.adminRequests.AddAccountRequest;
import generalPackage.adminResponses.AddAccountResponse;
import generalPackage.adminResponses.CoreError;
import generalPackage.database.Database;

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
