package myApp.core.services;
import myApp.core.database.DataBase;
import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.OpenAccountValidator;

import java.util.List;


public class OpenAccountService {

    private DataBase dataBase;
    private OpenAccountValidator validator;

    public OpenAccountService(DataBase dataBase, OpenAccountValidator validator) {
        this.dataBase = dataBase;
        this.validator = validator;
    }

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

