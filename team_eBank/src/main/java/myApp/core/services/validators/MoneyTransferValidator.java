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
        validateAmount(request).ifPresent(errors::add);
        return errors;
    }

    //need to fix
    private Optional<CoreError> validateFirstAccountID(MoneyTransferRequest request) {
        return Optional.empty();
    }

    //need to fix
    private Optional<CoreError> validateAmount(MoneyTransferRequest request) {
        return Optional.empty();
    }

    //need to fix
    private Optional<CoreError> validateAnotherPersonalCode(MoneyTransferRequest request) {
        return Optional.empty();
    }

}
