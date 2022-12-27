package myApp.core.services.validators;

import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class MoneyTransferValidator {

    public List<CoreError> validate(MoneyTransferRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateAnotherPersonalCode(request).ifPresent(errors::add);
        validateAmount(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateAnotherPersonalCode(MoneyTransferRequest request) {
        return request.getAnotherPersonalCode() != null
                && !request.getAnotherPersonalCode().isBlank()
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Another personal code",
                "Another personal code must not be empty"));
    }

    private Optional<CoreError> validateAmount(MoneyTransferRequest request) {
        return request.getValue() > 0
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Value",
                "Value must not be empty, and must be bigger than 0"));
    }

}
