package lv.javaguru.java2.eBooking.core.services.validators;

import lv.javaguru.java2.eBooking.core.requests.client_request.ClientSearchRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ClientSearchValidator {

    public List<CoreError> validate(ClientSearchRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
        return errors;
    }

    public List<CoreError> validateSearchFields(ClientSearchRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getClientEmail()) && isEmpty(request.getClientPhoneNumber())) {
            errors.add(new CoreError("email: ", ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY));
            errors.add(new CoreError("phone number: ", ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY));
        }
        return errors;
    }

    public boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
