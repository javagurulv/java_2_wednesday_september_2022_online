package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.RemoveBankAccountResponse;

import java.util.List;

public class RemoveBankAccountService {
    private DataBase dataBase;
    private RemoveBankAccountValidator validator;

    public RemoveBankAccountService(DataBase dataBase, RemoveBankAccountValidator validator) {
        this.dataBase = dataBase;
        this.validator = validator;
    }

    public RemoveBankAccountResponse execute(RemoveBankAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            boolean isDeleted = dataBase.deleteBankAccount(request.getId());
            return new RemoveBankAccountResponse(isDeleted);
        } else {
            return new RemoveBankAccountResponse(errors);
        }
    }
}
