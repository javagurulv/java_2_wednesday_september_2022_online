package lv.javaguru.java2.eBooking.core.services.validators;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentRemoveRequest;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientRemoveRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentRemoveValidator {

    public List<CoreError> validate(AppointmentRemoveRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateAppointmentId(request).ifPresent(errors::add);
        return errors;
    }

    public Optional<CoreError> validateAppointmentId(AppointmentRemoveRequest request){
        return request.getAppointmentId() == null
                ? Optional.of(new CoreError("Appointment Id",
                AppointmentValidationResult.APPOINTMENT_ID_MUST_NOT_BE_EMPTY))
                : Optional.empty();
    }
}
