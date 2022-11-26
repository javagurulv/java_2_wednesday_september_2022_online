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

        boolean isStartDateValid = validateAllStartDateValidations(request, errors);

        validateEndDateIsPresent(request).ifPresent(errors::add);
        if (validateEndDateIsPresent(request).isEmpty()) {
            validateEndDateFormat(request).ifPresent(errors::add);

            if (validateEndDateFormat(request).isEmpty()) {
                validateEndDateNotInPast(request).ifPresent(errors::add);

                if (validateEndDateNotInPast(request).isEmpty() && isStartDateValid) {
                    validateEndDateIsLessThanTwoMonthsForwardAfterStartDate(request).ifPresent(errors::add);

                    if (validateEndDateIsLessThanTwoMonthsForwardAfterStartDate(request).isEmpty()) {
                        validateEndDateIsNotEqualStartDate(request).ifPresent(errors::add);

                        if (validateEndDateIsNotEqualStartDate(request).isEmpty()) {
                            validateEndDateIsNotBeforeStartDate(request).ifPresent(errors::add);
                        }
                    }
                }
            }
        }

        if (request.getPaging() != null) {
            errors.addAll(pagingValidator.validate(request.getPaging()));
        }
        return errors;
    }

    private boolean validateAllStartDateValidations(GeneralRentVehicleRequest request, List<CoreError> errors) {

        validateStartDateIsPresent(request).ifPresent(errors::add);

        if (validateStartDateIsPresent(request).isEmpty()) {
            validateStartDateFormat(request).ifPresent(errors::add);

            if (validateStartDateFormat(request).isEmpty()) {
                validateStartDateNotInPast(request).ifPresent(errors::add);

                if (validateStartDateNotInPast(request).isEmpty()) {
                    validateStartDateIsLessThanOneYearForward(request).ifPresent(errors::add);
                    return validateStartDateIsLessThanOneYearForward(request).isEmpty();
                }
            }
        }
        return false;
    }

    private Optional<CoreError> validateStartDateIsPresent(GeneralRentVehicleRequest request) {
        return (request.getRentStartDate() == null || request.getRentStartDate().isBlank())
                ? Optional.of(new CoreError("Start date", "can't be empty or not present"))
                : Optional.empty();
    }

    private Optional<CoreError> validateStartDateFormat(GeneralRentVehicleRequest request) {
        return !GenericValidator.isDate(request.getRentStartDate(), "dd/MM/yyyy", true)
                ? Optional.of(new CoreError("Start date", "has to be valid and in format dd/MM/yyyy"))
                : Optional.empty();

    }

    private Optional<CoreError> validateStartDateNotInPast(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        return (LocalDate.now().isAfter(startDate))
                ? Optional.of(new CoreError("Start date", "can't be before current day"))
                : Optional.empty();
    }

    private Optional<CoreError> validateStartDateIsLessThanOneYearForward(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        return startDate.isAfter(LocalDate.now().plusYears(1))
                ? Optional.of(new CoreError("Start date", "has to be within one year from now"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateIsPresent(GeneralRentVehicleRequest request) {
        return (request.getRentEndDate() == null || request.getRentEndDate().isBlank())
                ? Optional.of(new CoreError("End date", "can't be empty or not present"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateFormat(GeneralRentVehicleRequest request) {
        return !GenericValidator.isDate(request.getRentEndDate(), "dd/MM/yyyy", true)
                ? Optional.of(new CoreError("End date", "has to be valid and in format dd/MM/yyyy"))
                : Optional.empty();

    }

    private Optional<CoreError> validateEndDateNotInPast(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);
        return (LocalDate.now().isAfter(endDate))
                ? Optional.of(new CoreError("End date", "can't be before current day"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateIsLessThanTwoMonthsForwardAfterStartDate(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);
        return endDate.isAfter(startDate.plusMonths(2))
                ? Optional.of(new CoreError("End date", "has to be within two months from rent start date"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateIsNotEqualStartDate(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);
        return (endDate.isEqual(startDate))
                ? Optional.of(new CoreError("Start and End date", "must`n be equal"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEndDateIsNotBeforeStartDate(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);
        return (endDate.isBefore(startDate))
                ? Optional.of(new CoreError("End date", "can't be before start date"))
                : Optional.empty();

    }

}
