package lv.javaguru.java2.eBooking.core.services.appointment.add;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AddAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddAppointmentValidator {

    public List<CoreError> validate(AddAppointmentRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateMasterNameEntry(request).ifPresent(coreError -> errors.add(coreError));
        validateServiceTypeEntry(request).ifPresent(coreError -> errors.add(coreError));
        return errors;
    }

    public Optional<CoreError> validateMasterNameEntry(AddAppointmentRequest request) {
        AppointmentValidationResult result = AppointmentRegistrationValidator.masterNameIsNotEmpty()
                .and(AppointmentRegistrationValidator.masterNameEntryContainingValidSymbols())
                .apply(new Appointment(request.getMasterName(), request.getTypeOfService()));
        return (request.getMasterName() == null
                || request.getMasterName().isEmpty()
                || result != AppointmentValidationResult.SUCCESS)
                ? Optional.of(new CoreError("Master name: ", result))
                : Optional.empty();
    }

    public Optional<CoreError> validateServiceTypeEntry(AddAppointmentRequest request) {
        AppointmentValidationResult result = AppointmentRegistrationValidator.appointmentServiceTypeIsNotEmpty()
                .and(AppointmentRegistrationValidator.appointmentServiceTypeEntryContainingValidSymbols())
                .apply(new Appointment(request.getMasterName(), request.getTypeOfService()));
        return (request.getTypeOfService() == null
                || request.getTypeOfService().isEmpty()
                || result != AppointmentValidationResult.SUCCESS)
                ? Optional.of(new CoreError("Service type: ", result))
                : Optional.empty();
    }
}
