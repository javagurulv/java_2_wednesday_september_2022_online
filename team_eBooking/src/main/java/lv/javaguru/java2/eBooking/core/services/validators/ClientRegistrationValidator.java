package lv.javaguru.java2.eBooking.core.services.validators;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.services.client.ClientGetAllService;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface ClientRegistrationValidator extends Function<Client,ClientValidationResult> {


    static ClientRegistrationValidator emailFieldIsNotEmpty() {
        return client -> !client.getClientEmail().isEmpty()
                ? ClientValidationResult.SUCCESS
                : ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY;
    }

    static ClientRegistrationValidator isEmailContainingValidSymbols() {
        return client -> client.getClientEmail().matches("^[@.a-zA-Z0-9]+$")
                ? ClientValidationResult.SUCCESS
                :ClientValidationResult.EMAIL_DOES_NOT_CONTAIN_VALID_SYMBOLS;
    }
    static ClientRegistrationValidator isEmailLengthValid(){
        return client -> client.getClientEmail().length()>8
                ? ClientValidationResult.SUCCESS
                : ClientValidationResult.EMAIL_LENGTH_IS_NOT_VALID;
    }

    static ClientRegistrationValidator isEmailContainingAtSign(){
        return client -> client.getClientEmail().contains("@")
                ? ClientValidationResult.SUCCESS
                : ClientValidationResult.EMAIL_DOES_NOT_CONTAIN_AT_SIGN;
    }

    static ClientRegistrationValidator phoneNumberFieldIsNotEmpty(){
        return client -> !client.getClientPhoneNumber().isEmpty()
                ? ClientValidationResult.SUCCESS
                : ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY;
    }
    static  ClientRegistrationValidator isPhoneNumberRegionCodeValid(){
        return client -> client.getClientPhoneNumber().contains("0037")
                ? ClientValidationResult.SUCCESS
                : ClientValidationResult.PHONE_NUMBER_REGION_CODE_IS_NOT_VALID;
    }

    static ClientRegistrationValidator isPhoneNumberLengthValid() {
        return client ->client.getClientPhoneNumber().length() == 13
                ? ClientValidationResult.SUCCESS
                : ClientValidationResult.PHONE_NUMBER_LENGTH_IS_NOT_VALID;
    }

    static ClientRegistrationValidator isPhoneNumberContainingValidSymbols(){
        return  client -> client.getClientPhoneNumber().matches("^[0-9+]+$")
                ? ClientValidationResult.SUCCESS
                :ClientValidationResult.PHONE_NUMBER_USED_SYMBOLS_NOT_VALID;
    }

    default ClientRegistrationValidator and (ClientRegistrationValidator other){
        return client -> {
            ClientValidationResult result = this.apply(client);
            return result.equals(ClientValidationResult.SUCCESS) ? other.apply(client) : result;
        };
    }
}
