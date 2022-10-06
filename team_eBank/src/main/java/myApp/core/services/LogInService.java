package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.domain.BankAccount;
import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.LogInResponse;
import myApp.core.services.validators.LogInValidator;

import java.util.List;
import java.util.Optional;

public class LogInService {

    private DataBase dataBase;
    private UserService userService;
    private LogInValidator validator;

    public LogInService(DataBase dataBase, UserService userService, LogInValidator validator) {
        this.dataBase = dataBase;
        this.userService = userService;
        this.validator = validator;
    }

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
