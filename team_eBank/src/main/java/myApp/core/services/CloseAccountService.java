package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.CloseAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CloseAccountService {

    @Autowired
    private DataBase dataBase;
    @Autowired
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
