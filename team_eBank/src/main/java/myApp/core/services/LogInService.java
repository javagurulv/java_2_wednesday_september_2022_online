package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.LogInResponse;
import myApp.core.services.validators.LogInValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogInService {

    @Autowired
    private DataBase dataBase;
    @Autowired
    private UserService userService;
    @Autowired
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
