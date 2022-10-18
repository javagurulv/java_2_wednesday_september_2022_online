package lv.javaguru.java2.eBooking.core.services.appointment.search;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.Ordering;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.SearchAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.services.appointment.add.AppointmentValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentSearchRequestValidator {

    public List<CoreError> validate(SearchAppointmentRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
        if (request.getOrdering() != null) {
            validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
        }
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

    public Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("Order by",
                AppointmentValidationResult.APPOINTMENT_ORDERBY_MUST_NOT_BE_EMPTY))
                : Optional.empty();
    }

}
