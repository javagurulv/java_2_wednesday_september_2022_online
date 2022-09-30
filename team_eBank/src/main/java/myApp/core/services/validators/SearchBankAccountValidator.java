package myApp.core.services.validators;

import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchBankAccountValidator {

    public List<CoreError> validate(SearchBankAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(SearchBankAccountRequest request) {
        if (request.getName() != null) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Name", "Name may contains only letters " +
                    "and cannot be empty"));
        }
    }

    private Optional<CoreError> validateSurname(SearchBankAccountRequest request) {
        if (request.getSurname() != null) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Surname", "Surname may contains only letters " +
                    "and cannot be empty"));
        }
    }

    private Optional<CoreError> validatePersonalCode(SearchBankAccountRequest request) {
        if (request.getName() != null) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Personal code", "Personal code may contains only numbers " +
                    "and cannot be empty"));
        }
    }
}
