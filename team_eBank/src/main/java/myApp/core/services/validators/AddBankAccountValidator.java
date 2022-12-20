package myApp.core.services.validators;

import myApp.core.requests.AddBankAccountRequest;
import myApp.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddBankAccountValidator {

    public List<CoreError> validate(AddBankAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validateName(AddBankAccountRequest request) {
        return  request.getName() != null
                && !request.getName().isBlank()
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Name",
                "Name can only contain letters and must not be empty"));
    }

    private Optional<CoreError> validateSurname(AddBankAccountRequest request) {
        return request.getSurname() != null
                && !request.getSurname().isBlank()
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Surname",
                "Surname can only contain letters and must not be empty"));
    }

    private Optional<CoreError> validatePersonalCode(AddBankAccountRequest request) {
        return //request.getPersonalCode().matches("^\\d\\d\\d\\d\\d\\d\\-\\d\\d\\d\\d\\d$") &&
                request.getPersonalCode() != null
                        && !request.getPersonalCode().isBlank()
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Personal Code",
                "Personal code can only contain numbers and must not be empty"));
    }
}