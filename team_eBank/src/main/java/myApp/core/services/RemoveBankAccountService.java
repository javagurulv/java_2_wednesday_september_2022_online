package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.RemoveBankAccountResponse;
import myApp.core.services.validators.RemoveBankAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RemoveBankAccountService {

    @Autowired
    private DataBase dataBase;
    @Autowired
    private RemoveBankAccountValidator validator;

    public RemoveBankAccountResponse execute(RemoveBankAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            boolean isDeleted = dataBase.deleteBankAccount(request.getPersonalCode());
            return new RemoveBankAccountResponse(isDeleted);
        } else {
            return new RemoveBankAccountResponse(errors);
        }
    }
}
