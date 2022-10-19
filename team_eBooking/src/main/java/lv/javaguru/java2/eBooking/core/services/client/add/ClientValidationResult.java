package lv.javaguru.java2.eBooking.core.services.client.add;

public enum ClientValidationResult {
    SUCCESS,
    EMAIL_MUST_NOT_BE_EMPTY,
    EMAIL_DOES_NOT_CONTAIN_VALID_SYMBOLS,
    EMAIL_DOES_NOT_CONTAIN_AT_SIGN,
    EMAIL_LENGTH_IS_NOT_VALID,
    PHONE_NUMBER_MUST_NOT_BE_EMPTY,
    PHONE_NUMBER_LENGTH_IS_NOT_VALID,
    PHONE_NUMBER_REGION_CODE_IS_NOT_VALID,
    PHONE_NUMBER_USED_SYMBOLS_NOT_VALID,

    CLIENT_ID_MUST_NOT_BE_EMPTY
}
