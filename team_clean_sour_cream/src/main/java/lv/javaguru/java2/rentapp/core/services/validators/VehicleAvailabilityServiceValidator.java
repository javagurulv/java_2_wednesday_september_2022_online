package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.apache.commons.validator.GenericValidator;
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

    public List<CoreError> validate(GeneralRentVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateStartDateIsPresent(request).ifPresent(errors::add);
        validateStartDateFormat(request).ifPresent(errors::add);
        if (validateStartDateIsPresent(request).isEmpty() && validateStartDateFormat(request).isEmpty()) {
            validateStartDateNotInPast(request).ifPresent(errors::add);
            validateStartDateIsNotToFarShouldBeLessThanOneYearForward(request).ifPresent(errors::add);
        }

        validateEndDateIsPresent(request).ifPresent(errors::add);
        validateEndDateFormat(request).ifPresent(errors::add);
        if (validateEndDateIsPresent(request).isEmpty() && validateEndDateFormat(request).isEmpty()) {
            validateEndDateNotInPast(request).ifPresent(errors::add);
            validateEndDateIsNotToFarShouldBeLessThanTwoMonthsForward(request).ifPresent(errors::add);
        }

        if (validateStartDateIsPresent(request).isEmpty() && validateStartDateFormat(request).isEmpty() &&
                validateEndDateIsPresent(request).isEmpty() && validateEndDateFormat(request).isEmpty()) {
            validateEndDateIsNotBeforeStartDate(request).ifPresent(errors::add);
            validateEndDateIsNotEqualStartDate(request).ifPresent(errors::add);
        }

        if (request.getPaging() != null) {
            errors.addAll(pagingValidator.validate(request.getPaging()));
        }
        return errors;
    }

    private Optional<CoreError> validateStartDateIsPresent(GeneralRentVehicleRequest request) {
        return (request.getRentStartDate() == null || request.getRentStartDate().isBlank())
                ? Optional.of(new CoreError("Start date", "can't be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateStartDateFormat(GeneralRentVehicleRequest request) {
        return !GenericValidator.isDate(request.getRentStartDate(), "dd/MM/yyyy", true)
                ? Optional.of(new CoreError("Start date", "has to be in format dd/MM/yyyy "))
                : Optional.empty();

    }

    private Optional<CoreError> validateStartDateNotInPast(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        return (LocalDate.now().isAfter(startDate))
                ? Optional.of(new CoreError("Start date", "can't be before current day"))
                : Optional.empty();
    }

    private Optional<CoreError> validateStartDateIsNotToFarShouldBeLessThanOneYearForward(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        return startDate.isAfter(LocalDate.now().plusYears(1))
                ? Optional.of(new CoreError("Start date", "has to be within one year from now "))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateIsPresent(GeneralRentVehicleRequest request) {
        return (request.getRentEndDate() == null || request.getRentEndDate().isBlank())
                ? Optional.of(new CoreError("End date", "can't be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateFormat(GeneralRentVehicleRequest request) {
        return !GenericValidator.isDate(request.getRentEndDate(), "dd/MM/yyyy", true)
                ? Optional.of(new CoreError("End date", "has to be in format dd/MM/yyyy "))
                : Optional.empty();

    }

    private Optional<CoreError> validateEndDateNotInPast(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);
        return (LocalDate.now().isAfter(endDate))
                ? Optional.of(new CoreError("End date", "can't be before current day"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateIsNotToFarShouldBeLessThanTwoMonthsForward(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);
        return endDate.isAfter(LocalDate.now().plusMonths(2))
                ? Optional.of(new CoreError("End date", "has to be within two months from rent start date"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateIsNotBeforeStartDate(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);
        return (endDate.isBefore(startDate))
                ? Optional.of(new CoreError("End date ", "can't be before start date"))
                : Optional.empty();

    }

    private Optional<CoreError> validateEndDateIsNotEqualStartDate(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);
        return (endDate.isEqual(startDate))
                ? Optional.of(new CoreError("Start and End date ", "must`n be equal"))
                : Optional.empty();
    }

}
