package lv.javaguru.java2.eBooking.core.services.validators;

import lv.javaguru.java2.eBooking.core.database.InMemoryDatabase;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientAddRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClientAddValidator{

    public List<CoreError> validate(ClientAddRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateClientEMail(request).ifPresent(coreError -> errors.add(coreError));
        validateClientPhoneNumber(request).ifPresent(coreError -> errors.add(coreError));
        return errors;
    }

    public Optional<CoreError> validateClientEMail(ClientAddRequest request) {
        ClientValidationResult result = ClientRegistrationValidator.emailFieldIsNotEmpty()
                .and(ClientRegistrationValidator.isEmailContainingAtSign())
                .and(ClientRegistrationValidator.isEmailContainingValidSymbols())
                .and(ClientRegistrationValidator.isEmailLengthValid())
                .apply(new Client(request.getClientEmail(), request.getClientPhoneNumber()));
        return isEmpty(request.getClientEmail())
                || isEmpty(request.getClientPhoneNumber())
                || result != ClientValidationResult.SUCCESS
                ? Optional.of(new CoreError("email", result))
                : Optional.empty();
    }

    public Optional<CoreError> validateClientPhoneNumber(ClientAddRequest request) {
        ClientValidationResult result = ClientRegistrationValidator.phoneNumberFieldIsNotEmpty()
                .and(ClientRegistrationValidator.isPhoneNumberContainingValidSymbols())
                .and(ClientRegistrationValidator.isPhoneNumberLengthValid())
                .and(ClientRegistrationValidator.isPhoneNumberRegionCodeValid())
                .apply(new Client(request.getClientEmail(), request.getClientPhoneNumber()));
        return isEmpty(request.getClientEmail())
                || isEmpty(request.getClientPhoneNumber())
                || result != ClientValidationResult.SUCCESS
                ? Optional.of(new CoreError("telephone number", result))
                : Optional.empty();
    }
    public boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
