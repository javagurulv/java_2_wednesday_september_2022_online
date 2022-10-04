package myApp.core.services.validators;

import myApp.core.requests.Paging;
import myApp.core.responses.CoreError;
import myApp.core.requests.AddBankAccountRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddBankAccountValidator {

    public List<CoreError> validate(AddBankAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validateName(AddBankAccountRequest request) {
        return request.getName().matches("^[a-zA-Z]+$") && request.getName() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Name",
                "Name can only contain letters and must not be empty"));
    }

    private Optional<CoreError> validateSurname(AddBankAccountRequest request) {
        return request.getSurname().matches("^[a-zA-Z]+$") && request.getSurname() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Surname",
                "Surname can only contain letters and must not be empty"));
    }
}
