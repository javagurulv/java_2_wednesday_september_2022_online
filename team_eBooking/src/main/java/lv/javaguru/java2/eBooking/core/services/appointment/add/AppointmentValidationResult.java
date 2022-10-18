package lv.javaguru.java2.eBooking.core.services.appointment.add;

public enum AppointmentValidationResult {
    SUCCESS,

    MASTERNAME_MUST_NOT_BE_EMPTY,
    MASTERNAME_NOT_VALID_ENTRY_SYMBOLS,
    SERVICETYPE_MUST_NOT_BE_EMPTY,
    SERVICETYPE_NOT_VALID_ENTRY_SYMBOLS,

    APPOINTMENT_ID_MUST_NOT_BE_EMPTY,
    APPOINTMENT_SHOULD_CONTAIN_MASTERNAME_OR_SERVICETYPE,
    APPOINTMENT_ORDERBY_MUST_NOT_BE_EMPTY,

    APPOINTMENT_PAGENUMBER_MUST_NOT_BE_EMPTY,
    APPOINTMENT_PAGESIZE_MUST_NOT_BE_EMPTY,
    APPOINTMENT_PAGENUMBER_VALUE_IS_NOT_VALID,
    APPOINTMENT_PAGESIZE_VALUE_IS_NOT_VALID,

}
