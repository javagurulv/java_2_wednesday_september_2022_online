package myApp.core.services.validators;


import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LogInValidator {


    public List<CoreError> validate(LogInRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePersonalCode(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePersonalCode(LogInRequest request) {
        return !request.getPersonalCode().isEmpty() && request.getPersonalCode() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Personal code", "Personal code may contains only numbers and" +
                "cannot be empty"));
    }

    private Optional<CoreError> validatePassword(LogInRequest request) {
        return !request.getPersonalCode().isEmpty() && request.getPersonalCode() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Password", "Password cannot be empty"));
    }
}
