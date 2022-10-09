package lv.javaguru.java2.eBooking.core.services.client_service.search;

import lv.javaguru.java2.eBooking.core.requests.client_request.SearchClientRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.services.client_service.add.ClientValidationResult;

import java.util.ArrayList;
import java.util.List;

public class ClientSearchRequestValidator {

    public List<CoreError> validate(SearchClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
        return errors;
    }

    public List<CoreError> validateSearchFields(SearchClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (request.getClientEmail().isEmpty() && request.getClientPhoneNumber().isEmpty()) {
            errors.add(new CoreError("email: ", ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY));
            errors.add(new CoreError("phone number: ", ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY));
        }
        return errors;
    }
}
