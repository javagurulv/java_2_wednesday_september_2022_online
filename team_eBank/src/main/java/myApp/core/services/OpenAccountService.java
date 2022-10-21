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
            if (accountNullCheck(request.getPersonalCode())) {
                boolean result = dataBase.openAccount(request.getPersonalCode(), request.getAmount());
                return new OpenAccountResponse(result);
            }
        }
        return new OpenAccountResponse(errors);
    }

    private boolean accountNullCheck(String personalCode) {
        return dataBase.getAllBankAccounts().stream()
                .filter(b -> b.getPersonalCode().equals(personalCode))
                .anyMatch(b -> b.getAccount() == null);
    }
}

