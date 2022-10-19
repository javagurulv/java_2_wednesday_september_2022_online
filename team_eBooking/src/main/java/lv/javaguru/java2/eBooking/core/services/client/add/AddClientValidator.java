package lv.javaguru.java2.eBooking.core.services.client.add;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.AddClientRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;

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
        ClientValidationResult result = ClientRegistrationValidator.emailFieldIsNotEmpty()
                .and(ClientRegistrationValidator.isEmailContainingAtSign())
                .and(ClientRegistrationValidator.isEmailContainingValidSymbols())
                .and(ClientRegistrationValidator.isEmailLengthValid())
                .apply(new Client(request.getClientEmail(), request.getClientPhoneNumber()));
        return (isEmpty(request.getClientEmail())
                || isEmpty(request.getClientEmail())
                || result != ClientValidationResult.SUCCESS)
                ? Optional.of(new CoreError("email", result))
                : Optional.empty();
    }

    public Optional<CoreError> validateClientPhoneNumber(AddClientRequest request) {
        ClientValidationResult result = ClientRegistrationValidator.phoneNumberFieldIsNotEmpty()
                .and(ClientRegistrationValidator.isPhoneNumberContainingValidSymbols())
                .and(ClientRegistrationValidator.isPhoneNumberLengthValid())
                .and(ClientRegistrationValidator.isPhoneNumberRegionCodeValid())
                .apply(new Client(request.getClientEmail(), request.getClientPhoneNumber()));
        return (isEmpty( request.getClientPhoneNumber())
                || isEmpty(request.getClientPhoneNumber())
                || result != ClientValidationResult.SUCCESS)
                ? Optional.of(new CoreError("telephone number", result))
                : Optional.empty();
    }

    public boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
