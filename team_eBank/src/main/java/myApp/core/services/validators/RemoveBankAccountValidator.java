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

    private Optional<CoreError> validateId(RemoveBankAccountRequest request) {
        return request.getId() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Id", "Id must not be empty"));

    }
}
