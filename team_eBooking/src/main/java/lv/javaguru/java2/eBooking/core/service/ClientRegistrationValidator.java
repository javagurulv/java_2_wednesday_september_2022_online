package lv.javaguru.java2.eBooking.core.service;

import lv.javaguru.java2.eBooking.core.domain.Client;

import java.util.function.Function;

public interface ClientRegistrationValidator extends Function<Client,ClientValidationResult> {

    static ClientRegistrationValidator isEmailValid() {
        return client -> client.getClientEmail().contains("@")
                ? ClientValidationResult.SUCCESS
                :ClientValidationResult.EMAIL_NOT_VALID;
    }

    static ClientRegistrationValidator isPhoneNumberValid() {
        return client -> client.getClientPhoneNumber().contains("+37")
                ? ClientValidationResult.SUCCESS
                : ClientValidationResult.PHONE_NUMBER_NOT_VALID;

    }
    default ClientRegistrationValidator and (ClientRegistrationValidator other){
        return client -> {
            ClientValidationResult result = this.apply(client);
            return result.equals(ClientValidationResult.SUCCESS) ? other.apply(client) : result;
        };
    }
}
