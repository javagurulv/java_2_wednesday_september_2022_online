package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.services.validators.OpenAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpenAccountService {
    @Autowired
    private DataBase dataBase;
    @Autowired
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

