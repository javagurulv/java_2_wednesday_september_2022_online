package myApp.core.services.validators;

import myApp.core.responses.CoreError;
import myApp.core.requests.RemoveBankAccountRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveBankAccountValidator {

    public List<CoreError> validate(RemoveBankAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateID(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateID(RemoveBankAccountRequest request) {
        if (request.getPersonalCode() != null) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Field: Id", "id must not be empty"));
        }
    }
}
