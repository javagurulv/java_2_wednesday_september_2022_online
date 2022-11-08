package lv.javaguru.java2.eBooking.core.services.validators;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.Ordering;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.Paging;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentSearchRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class AppointmentSearchValidator {

    public List<CoreError> validate(AppointmentSearchRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
        if (request.getOrdering() != null) {
            validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateOrderBy(request.getOrdering()).ifPresent(errors::add);
        }

        if (request.getPaging() != null) {
            validateMandatoryPageNumber(request.getPaging()).ifPresent(errors::add);
            validateMandatoryPageSize(request.getPaging()).ifPresent(errors::add);
            validatePageNumber(request.getPaging()).ifPresent(errors::add);
            validatePageSize(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }

    public List<CoreError> validateSearchFields(AppointmentSearchRequest request) {
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

    public Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null)
                && !(ordering.getOrderBy().equals("Master name")
                || ordering.getOrderBy().equals("Type of service"))
                ? Optional.of(new CoreError("Order by",
                AppointmentValidationResult.APPOINTMENT_SHOULD_CONTAIN_MASTERNAME_OR_SERVICETYPE))
                : Optional.empty();
    }

    public Optional<CoreError> validateMandatoryPageNumber(Paging paging) {
        return (paging.getPageNumber() == null && paging.getPageSize() != null)
                ? Optional.of(new CoreError("Page number",
                AppointmentValidationResult.APPOINTMENT_PAGENUMBER_MUST_NOT_BE_EMPTY))
                : Optional.empty();
    }

    public Optional<CoreError> validateMandatoryPageSize(Paging paging) {
        return (paging.getPageSize() == null && paging.getPageNumber() != null)
                ? Optional.of(new CoreError("Page size",
                AppointmentValidationResult.APPOINTMENT_PAGESIZE_MUST_NOT_BE_EMPTY))
                : Optional.empty();
    }

    public Optional<CoreError> validatePageNumber(Paging paging) {
        return (paging.getPageNumber() != null && paging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("Page number",
                AppointmentValidationResult.APPOINTMENT_PAGENUMBER_MUST_BE_GREATER_THAN_ZERO))
                : Optional.empty();
    }

    public Optional<CoreError> validatePageSize(Paging paging) {
        return (paging.getPageSize() != null && paging.getPageSize() <= 0)
                ? Optional.of(new CoreError("Page size",
                AppointmentValidationResult.APPOINTMENT_PAGESIZE_MUST_BE_GREATER_THAN_ZERO))
                : Optional.empty();
    }
}
