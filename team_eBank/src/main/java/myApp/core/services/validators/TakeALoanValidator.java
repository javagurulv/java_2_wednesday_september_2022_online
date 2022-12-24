package myApp.core.services.validators;

import myApp.core.requests.TakeALoanRequest;
import myApp.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TakeALoanValidator {

    public List<CoreError> execute(TakeALoanRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateValue(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateValue(TakeALoanRequest request) {
        return request.getValue() != null
                && request.getValue() <= 10000
                ?Optional.empty()
                :Optional.of(new CoreError("Field: Value",
                "Must not be empty, the maximum you can take is 10000"));
    }
}
