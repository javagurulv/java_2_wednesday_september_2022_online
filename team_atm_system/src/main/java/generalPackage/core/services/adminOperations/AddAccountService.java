package generalPackage.core.services.adminOperations;


import generalPackage.Accounts;
import generalPackage.core.database.Database;
import generalPackage.core.requests.adminRequests.AddAccountRequest;
import generalPackage.core.responses.adminResponses.AddAccountResponse;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.services.adminOperations.adminValidators.AddAccountServiceValidator;
import generalPackage.dependencyInjection.DIComponent;
import generalPackage.dependencyInjection.DIDependency;

import java.util.List;


@DIComponent
public class AddAccountService {

    @DIDependency
    private Database database;
    @DIDependency
    private AddAccountServiceValidator validator;


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
