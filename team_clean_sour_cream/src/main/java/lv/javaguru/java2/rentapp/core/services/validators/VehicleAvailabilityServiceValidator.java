package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class VehicleAvailabilityServiceValidator {
    @Autowired
    private PagingValidator pagingValidator;

    public List<CoreError> execute(GeneralRentVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateStrartDateIsPresent(request).ifPresent(errors::add);
        validateStartDateFormat(request).ifPresent(errors::add);
        validateStartDateNotInPast(request).ifPresent(errors::add);
        validateStartDateIsNotToFar(request).ifPresent(errors::add);
        validateEndDateIsPresent(request).ifPresent(errors::add);
        validateEndDateFormat(request).ifPresent(errors::add);
        validateEndDateNotInPast(request).ifPresent(errors::add);
        validateEndDateIsNotBeforeStartDate(request).ifPresent(errors::add);
        if (request.getPaging() != null) {
            errors.addAll(pagingValidator.validate(request.getPaging()));
        }
        return errors;
    }

    private Optional<CoreError> validateStrartDateIsPresent(GeneralRentVehicleRequest request) {
        return (request.getRentStartDate() == null && request.getRentStartDate().isBlank())
                ? Optional.of(new CoreError("Start date", "can't be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateStartDateFormat(GeneralRentVehicleRequest request) {
        if (validateStrartDateIsPresent(request).isPresent()) {
            return Optional.empty();
        }
        DateValidator dateValidator = DateValidator.getInstance();
        return !GenericValidator.isDate(request.getRentStartDate(), "dd/MM/yyyy", true)
                ? Optional.of(new CoreError("Start date", "has to be in format dd/MM/yyyy "))
                : Optional.empty();

    }

    private Optional<CoreError> validateStartDateNotInPast(GeneralRentVehicleRequest request) {
        if (validateStrartDateIsPresent(request).isPresent() || validateStartDateFormat(request).isPresent()) {
            return Optional.empty();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        return (LocalDate.now().isAfter(startDate))
                ? Optional.of(new CoreError("Start date", "can't be before current day"))
                : Optional.empty();
    }

    private Optional<CoreError> validateStartDateIsNotToFar(GeneralRentVehicleRequest request) {
        if (validateStrartDateIsPresent(request).isPresent() || validateStartDateFormat(request).isPresent()) {
            return Optional.empty();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        return startDate.isAfter(LocalDate.now().plusYears(1))
                ? Optional.of(new CoreError("Start date", "has to be within one year from now "))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateIsPresent(GeneralRentVehicleRequest request) {
        return (request.getRentEndDate() == null && request.getRentEndDate().isBlank())
                ? Optional.of(new CoreError("End date", "can't be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateFormat(GeneralRentVehicleRequest request) {
        if (validateEndDateIsPresent(request).isPresent()) {
            return Optional.empty();
        }
        DateValidator dateValidator = DateValidator.getInstance();
        return !GenericValidator.isDate(request.getRentEndDate(), "dd/MM/yyyy", true)
                ? Optional.of(new CoreError("End date", "has to be in format dd/MM/yyyy "))
                : Optional.empty();

    }

    private Optional<CoreError> validateEndDateNotInPast(GeneralRentVehicleRequest request) {
        if (validateEndDateIsPresent(request).isPresent() || validateEndDateFormat(request).isPresent()) {
            return Optional.empty();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);
        return (LocalDate.now().isAfter(endDate))
                ? Optional.of(new CoreError("End date", "can't be before current day"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateIsNotBeforeStartDate(GeneralRentVehicleRequest request) {
        if (validateStrartDateIsPresent(request).isPresent() || validateStartDateFormat(request).isPresent() ||
                validateEndDateIsPresent(request).isPresent() || validateEndDateFormat(request).isPresent()) {
            return Optional.empty();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);
        return (endDate.isBefore(startDate))
                ? Optional.of(new CoreError("End date ", "can't be before start date"))
                : Optional.empty();

    }

}
