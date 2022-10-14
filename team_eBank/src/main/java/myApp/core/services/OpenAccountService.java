package myApp.core.services;
import myApp.core.database.DataBase;
import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.OpenAccountValidator;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class OpenAccountService {
    @DIDependency
    private DataBase dataBase;
    @DIDependency
    private OpenAccountValidator validator;

    public OpenAccountResponse execute(OpenAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            boolean result = dataBase.openAccount(request.getPersonalCode());
            return new OpenAccountResponse(result);
        } else {
            return new OpenAccountResponse(errors);
        }
    }
}

