package myApp.core.services.validators;


import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class LogInValidator {


    public List<CoreError> validate(LogInRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateLogin(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateLogin(LogInRequest request) {
        return !request.getLogin().isEmpty() && request.getLogin() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Login", "Login cannot be empty"));
    }

    private Optional<CoreError> validatePassword(LogInRequest request) {
        return !request.getPassword().isEmpty() && request.getPassword() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Password", "Password cannot be empty"));
    }


}
