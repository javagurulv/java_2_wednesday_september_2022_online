package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.LogInResponse;
import myApp.core.services.validators.LogInValidator;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.List;
import java.util.Optional;

@DIComponent
public class LogInService {
    @DIDependency
    private DataBase dataBase;
    @DIDependency
    private UserService userService;
    @DIDependency
    private LogInValidator validator;

    public LogInResponse execute(LogInRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LogInResponse(errors);
        }
        if (userService.logIn(request.getPersonalCode(), request.getPassword())) {
            return new LogInResponse(request.getPersonalCode());
        }
        return new LogInResponse("");
    }

}
