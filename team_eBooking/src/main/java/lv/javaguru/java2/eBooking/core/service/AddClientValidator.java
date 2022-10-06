package lv.javaguru.java2.eBooking.core.service;

import lv.javaguru.java2.eBooking.core.request.AddClientRequest;
import lv.javaguru.java2.eBooking.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddClientValidator {

    public List<CoreError> validate(AddClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateClientEMail(request).ifPresent(coreError -> errors.add(coreError));
        validateClientPhoneNumber(request).ifPresent(coreError -> errors.add(coreError));
        return errors;
    }

    public Optional<CoreError> validateClientEMail(AddClientRequest request) {
        return (request.getClientEmail() == null || request.getClientEmail().isEmpty())
                ? Optional.of(new CoreError("email", "Email field can not be empty"))
                : Optional.empty();
    }

    public Optional<CoreError> validateClientPhoneNumber(AddClientRequest request) {
        return request.getClientPhoneNumber() == null || request.getClientPhoneNumber().isEmpty()
                ? Optional.of(new CoreError("telephone number", "Phone number can not be empty"))
                : Optional.empty();
    }
}
