package myApp.core.services;

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
        validateSecondAccountID(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateFirstAccountID(MoneyTransferRequest request) {
        String number = String.valueOf(request.getUserID());
        return number.matches("[0-9]")
                ? Optional.empty()
                : Optional.of(new CoreError("1st account ID"
                , "Сan only contain numbers and cannot be empty"));
    }

    private Optional<CoreError> validateAmount(MoneyTransferRequest request) {
        String number = String.valueOf(request.getValue());
        return number.matches("[0-9]")
                ? Optional.empty()
                : Optional.of(new CoreError("1st account ID"
                , "Сan only contain numbers and cannot be empty"));
    }

    private Optional<CoreError> validateSecondAccountID(MoneyTransferRequest request) {
        String number = String.valueOf(request.getAnotherAccountID());
        return number.matches("[0-9]")
                ? Optional.empty()
                : Optional.of(new CoreError("2 account ID"
                , "Сan only contain numbers and cannot be empty"));
    }
}
