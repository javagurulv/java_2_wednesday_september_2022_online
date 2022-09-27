package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.CloseAccountValidator;

import java.util.List;

public class CloseAccountService {

    private DataBase dataBase;
    private CloseAccountValidator validator;

    public CloseAccountService(DataBase dataBase, CloseAccountValidator validator) {
        this.dataBase = dataBase;
        this.validator = validator;
    }

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
