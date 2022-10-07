package lv.javaguru.java2.eBooking.core.service;

import lv.javaguru.java2.eBooking.core.domain.Client;
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
        ClientValidationResult result = ClientRegistrationValidator.isEmailValid()
                .apply(new Client(request.getClientEmail(), request.getClientPhoneNumber()));
        return (request.getClientEmail() == null
                || request.getClientEmail().isEmpty()
                || result != ClientValidationResult.SUCCESS)
                ? Optional.of(new CoreError("email", result))
                : Optional.empty();
    }

    public Optional<CoreError> validateClientPhoneNumber(AddClientRequest request) {
        ClientValidationResult result = ClientRegistrationValidator.isPhoneNumberValid()
                .apply(new Client(request.getClientEmail(), request.getClientPhoneNumber()));
        return (request.getClientPhoneNumber() == null
                || request.getClientPhoneNumber().isEmpty()
                || result != ClientValidationResult.SUCCESS)
                ? Optional.of(new CoreError("telephone number", result))
                : Optional.empty();
    }


}
