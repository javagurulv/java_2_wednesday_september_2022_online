package myApp.core.services;

import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.LogInResponse;
import myApp.core.services.validators.LogInValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Component
public class LogInService {

    @Autowired
    private UserService userService;
    @Autowired
    private LogInValidator validator;

    public LogInResponse execute(LogInRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LogInResponse(errors);
        }
        String encodePassword =
                Base64.getEncoder().encodeToString(request.getPassword().getBytes(StandardCharsets.UTF_8));
        if (userService.logIn(request.getPersonalCode(), encodePassword)) {
            return new LogInResponse(request.getPersonalCode());
        }
        return new LogInResponse("");
    }
}