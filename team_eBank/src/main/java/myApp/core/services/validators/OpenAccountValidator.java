package myApp.core.services.validators;

import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class OpenAccountValidator {

    public List<CoreError> validate(OpenAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePersonalCode(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePersonalCode(OpenAccountRequest request) {
        return request.getPersonalCode() != null
                && request.getPersonalCode().matches("^\\d\\d\\d\\d\\d\\d\\-\\d\\d\\d\\d\\d$")
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Personal code",
                "Personal code must not be empty"));
    }

}
