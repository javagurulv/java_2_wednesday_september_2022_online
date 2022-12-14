package myApp.core.services.validators;

import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveBankAccountValidator {

    public List<CoreError> validate(RemoveBankAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }
/*
    private Optional<CoreError> validatePersonalCode(RemoveBankAccountRequest request) {
        return request.getPersonalCode() != null
                && request.getPersonalCode().matches("^\\d\\d\\d\\d\\d\\d\\-\\d\\d\\d\\d\\d$")
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Personal code", "Personal code can only contain numbers and" +
                "  must not be empty"));
    }
 */

    private Optional<CoreError> validateId(RemoveBankAccountRequest request) {
        return request.getId() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Personal code", "Personal code can only contain numbers and" +
                "  must not be empty"));

    }
}
