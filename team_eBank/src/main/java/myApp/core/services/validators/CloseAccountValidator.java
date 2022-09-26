package myApp.core.services.validators;

import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CloseAccountValidator {

    public List<CoreError> validate(CloseAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePersonalCode(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePersonalCode(CloseAccountRequest request) {
        if (request.getPersonalCode() != null) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Field: Personal code",
                    "personal code must not be empty"));
        }
    }
}
