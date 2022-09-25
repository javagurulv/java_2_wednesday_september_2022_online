package myApp.core.services.validators;

import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OpenAccountValidator {

    public List<CoreError> validate(OpenAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePersonalCode(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePersonalCode(OpenAccountRequest request) {
        if (request.getPersonalCode() != null) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Field: Personal code",
                    "personal code must not be empty"));
        }
    }
}
