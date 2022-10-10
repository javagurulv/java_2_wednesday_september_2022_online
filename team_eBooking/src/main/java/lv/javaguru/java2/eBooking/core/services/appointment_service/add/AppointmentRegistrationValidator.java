package lv.javaguru.java2.eBooking.core.services.appointment_service.add;

import lv.javaguru.java2.eBooking.core.domain.Appointment;

import java.util.function.Function;

public interface AppointmentRegistrationValidator extends Function<Appointment, AppointmentValidationResult> {

    static AppointmentRegistrationValidator masterNameIsNotEmpty() {
        return appointment -> !appointment.getMasterName().isEmpty()
                ? AppointmentValidationResult.SUCCESS
                : AppointmentValidationResult.MASTERNAME_MUST_NOT_BE_EMPTY;
    }

    static AppointmentRegistrationValidator masterNameEntryContainingValidSymbols() {
        return appointment -> appointment.getMasterName().matches("^[a-zA-Z]+$")
                ? AppointmentValidationResult.SUCCESS
                : AppointmentValidationResult.MASTERNAME_NOT_VALID_ENTRY_SYMBOLS;
    }

    static AppointmentRegistrationValidator appointmentServiceTypeIsNotEmpty() {
        return appointment -> !appointment.getTypeOfService().isEmpty()
                ? AppointmentValidationResult.SUCCESS
                : AppointmentValidationResult.SERVICETYPE_MUST_NOT_BE_EMPTY;
    }

    static AppointmentRegistrationValidator appointmentServiceTypeEntryContainingValidSymbols() {
        return appointment -> appointment.getTypeOfService().matches("^[a-zA-Z]+$")
                ? AppointmentValidationResult.SUCCESS
                : AppointmentValidationResult.SERVICETYPE_NOT_VALID_ENTRY_SYMBOLS;
    }

    default AppointmentRegistrationValidator and(AppointmentRegistrationValidator other) {
        return appointment -> {
            AppointmentValidationResult result = this.apply(appointment);
            return result.equals(AppointmentValidationResult.SUCCESS)
                    ? other.apply(appointment)
                    : result;
        };
    }
}
