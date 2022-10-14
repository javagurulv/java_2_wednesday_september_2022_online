package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.RemoveBankAccountResponse;
import myApp.core.services.validators.RemoveBankAccountValidator;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.List;
@DIComponent
public class RemoveBankAccountService {
    @DIDependency
    private DataBase dataBase;
    @DIDependency
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
