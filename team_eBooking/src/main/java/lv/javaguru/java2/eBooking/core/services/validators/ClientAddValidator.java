package lv.javaguru.java2.eBooking.core.services.validators;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.database.InMemoryDatabase;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientAddRequest;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientGetAllRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ClientAddValidator extends InMemoryDatabase {

    public List<CoreError> validate(ClientAddRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateClientEMail(request).ifPresent(coreError -> errors.add(coreError));
        validateClientPhoneNumber(request).ifPresent(coreError -> errors.add(coreError));
        validateClientDuplication(request).ifPresent(coreError -> errors.add(coreError));

        return errors;
    }

    public Optional<CoreError> validateClientEMail(ClientAddRequest request) {
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

    public Optional<CoreError> validateClientPhoneNumber(ClientAddRequest request) {
        ClientValidationResult result = ClientRegistrationValidator.phoneNumberFieldIsNotEmpty()
                .and(ClientRegistrationValidator.isPhoneNumberContainingValidSymbols())
                .and(ClientRegistrationValidator.isPhoneNumberLengthValid())
                .and(ClientRegistrationValidator.isPhoneNumberRegionCodeValid())
                .apply(new Client(request.getClientEmail(), request.getClientPhoneNumber()));
        return (isEmpty(request.getClientPhoneNumber())
                || isEmpty(request.getClientPhoneNumber())
                || result != ClientValidationResult.SUCCESS)
                ? Optional.of(new CoreError("telephone number", result))
                : Optional.empty();
    }

    public Optional<CoreError> validateClientDuplication(ClientAddRequest request) {
        ClientValidationResult result = ClientRegistrationValidatorV2.eMailDuplicated()
                .and(ClientRegistrationValidatorV2.phoneNumberDuplicated())
                .apply(getAllClients(),
                        new Client(request.getClientEmail(),
                                request.getClientPhoneNumber()));

        return result != ClientValidationResult.SUCCESS
                ? Optional.of(new CoreError("Duplication found", result))
                : Optional.empty();
    }

    public boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
