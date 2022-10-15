package myApp.core.services.validators;

import myApp.core.responses.CoreError;
import myApp.core.requests.RemoveBankAccountRequest;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@DIComponent
public class RemoveBankAccountValidator {

    public List<CoreError> validate(RemoveBankAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePersonalCode(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePersonalCode(RemoveBankAccountRequest request) {
        return request.getPersonalCode() != null
                && request.getPersonalCode().matches("^\\d\\d\\d\\d\\d\\d\\-\\d\\d\\d\\d\\d$")
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Personal code", "Personal code can only contain numbers and" +
                "  must not be empty"));
    }

}
