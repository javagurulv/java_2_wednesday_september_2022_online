package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.CloseAccountValidator;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.List;
@DIComponent
public class CloseAccountService {
    @DIDependency
    private DataBase dataBase;
    @DIDependency
    private CloseAccountValidator validator;

    public CloseAccountResponse execute(CloseAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            boolean result = dataBase.closeAccount(request.getPersonalCode());
            return new CloseAccountResponse(result);
        } else {
            return new CloseAccountResponse(errors);
        }
    }

}
