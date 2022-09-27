package myApp.core.services.validators;

import myApp.core.responses.CoreError;
import myApp.core.requests.MoneyTransferRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MoneyTransferValidator {

    public List<CoreError> validate(MoneyTransferRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateFirstAccountID(request).ifPresent(errors::add);
        validateAnotherPersonalCode(request).ifPresent(errors::add);
        validateAmount(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateFirstAccountID(MoneyTransferRequest request) {
        if (request.getPersonalCode() != null) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Field: Personal code",
                    "Your personal code must not be empty"));
        }
    }
    private Optional<CoreError> validateAnotherPersonalCode(MoneyTransferRequest request) {
        if (request.getAnotherPersonalCode() != null) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Field: Another personal code",
                    "another personal code must not be empty"));
        }
    }

    private Optional<CoreError> validateAmount(MoneyTransferRequest request) {
        if (request.getValue() > 0) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Field: Value",
                    "value must not be empty"));
        }
    }
}
