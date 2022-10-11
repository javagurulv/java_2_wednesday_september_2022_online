package lv.javaguru.java2.eBooking.core.services.appointment_service.add;

public enum AppointmentValidationResult {
    SUCCESS,
    MASTERNAME_MUST_NOT_BE_EMPTY,
    MASTERNAME_NOT_VALID_ENTRY_SYMBOLS,
    SERVICETYPE_MUST_NOT_BE_EMPTY,
    SERVICETYPE_NOT_VALID_ENTRY_SYMBOLS,

    APPOINTMENT_ID_MUST_NOT_BE_EMPTY
}
