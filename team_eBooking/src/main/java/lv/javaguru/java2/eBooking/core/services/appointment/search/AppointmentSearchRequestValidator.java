package lv.javaguru.java2.eBooking.core.services.appointment.search;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.SearchAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.services.appointment.add.AppointmentValidationResult;

import java.util.ArrayList;
import java.util.List;

public class AppointmentSearchRequestValidator {

    public List<CoreError> validate(SearchAppointmentRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
        return errors;
    }

    public List<CoreError> validateSearchFields(SearchAppointmentRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getMasterName()) && isEmpty(request.getTypeOfService())) {
            errors.add(new CoreError("Master name: ", AppointmentValidationResult.MASTERNAME_MUST_NOT_BE_EMPTY));
            errors.add(new CoreError("Type of service: ", AppointmentValidationResult.SERVICETYPE_MUST_NOT_BE_EMPTY));
        }
        return errors;
    }

    public boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

}
