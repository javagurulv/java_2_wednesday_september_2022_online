package lv.javaguru.java2.eBooking.core.services.validators;

import lv.javaguru.java2.eBooking.core.domain.Client;

import java.util.List;
import java.util.function.BiFunction;

public interface ClientRegistrationValidatorV2 extends BiFunction<List<Client>, Client, ClientValidationResult> {

    static ClientRegistrationValidatorV2 eMailDuplicated() {
        return (clients, client) ->
                clients.stream()
                        .anyMatch(client1 -> client1.getClientEmail().equals(client.getClientEmail()))
                        ? ClientValidationResult.CLIENT_EMAIL_MUST_NOT_BE_DUPLICATED
                        : ClientValidationResult.SUCCESS;
    }

    static ClientRegistrationValidatorV2 phoneNumberDuplicated() {
        return (clients, client) ->
                clients.stream()
                        .anyMatch(client1 -> client1.getClientPhoneNumber().equals(client.getClientPhoneNumber()))
                        ? ClientValidationResult.CLIENT_EMAIL_MUST_NOT_BE_DUPLICATED
                        : ClientValidationResult.SUCCESS;
    }

    default ClientRegistrationValidatorV2 and (ClientRegistrationValidatorV2 other) {
        return (clients, client) -> {
            ClientValidationResult result = this.apply(clients, client);
            return result.equals(ClientValidationResult.SUCCESS)
                    ? other.apply(clients, client)
                    : result;
        };
    }
}
