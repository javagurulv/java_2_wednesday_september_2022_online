package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.VehicleAvailabilityService;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RentVehicleValidator {

    @Autowired private VehicleAvailabilityService vehicleAvailabilityService;

    public List<CoreError> validate(GeneralRentVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateIdNotEmpty(request).ifPresent(errors::add);
        if (validateIdNotEmpty(request).isEmpty()) {
            validateChosenIdIsAmongProvided(request).ifPresent(errors::add);
            if (validateChosenIdIsAmongProvided(request).isEmpty()) {
                validateVehicleIsStillAvailable(request).ifPresent(errors::add);
            }
        }

        validatePersonalIdNotEmpty(request).ifPresent(errors::add);
        if (validatePersonalIdNotEmpty(request).isEmpty()) {
            validatePersonalIdFormat(request).ifPresent(errors::add);
        }

        validateFirstNameNotEmpty(request).ifPresent(errors::add);
        if (validateFirstNameNotEmpty(request).isEmpty()) {
            validateFirstNameFormat(request).ifPresent(errors::add);
        }

        validateLastNameNotEmpty(request).ifPresent(errors::add);
        if (validateLastNameNotEmpty(request).isEmpty()) {
            validateLastNameFormat(request).ifPresent(errors::add);
        }

        validateEmailNotEmpty(request).ifPresent(errors::add);
        if (validateEmailNotEmpty(request).isEmpty()) {
            validateEmailFormat(request).ifPresent(errors::add);
        }

        validatePhoneNumberNotEmpty(request).ifPresent(errors::add);
        if (validatePhoneNumberNotEmpty(request).isEmpty()) {
            validatePhoneNumberFormat(request).ifPresent(errors::add);
        }

        return errors;
    }

    private Optional<CoreError> validateIdNotEmpty(GeneralRentVehicleRequest request) {
        return request.getVehicleId() == null
                ? Optional.of(new CoreError("Vehicle id", "cannot be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateChosenIdIsAmongProvided(GeneralRentVehicleRequest request) {
        Long id = request.getVehicleId();
        Optional<Vehicle> vehicleToRent = request.getAvailableVehicles().stream().filter(vehicle -> vehicle.getId().equals(id)).findFirst();
        return vehicleToRent.isEmpty()
                ? Optional.of(new CoreError("Vehicle with id " + id, "was not one of the provided options"))
                : Optional.empty();
    }

    private Optional<CoreError> validateVehicleIsStillAvailable(GeneralRentVehicleRequest request) {
        Long id = request.getVehicleId();
        List<Vehicle> updatedAvailableVehicles = vehicleAvailabilityService.execute(request, request.getAvailableVehicles()).getVehicles();
        Optional<Vehicle> vehicleToRent = updatedAvailableVehicles.stream().filter(vehicle -> vehicle.getId().equals(id)).findFirst();
        return vehicleToRent.isEmpty()
                ? Optional.of(new CoreError("Vehicle with id " + id, "is not longer available"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEmailNotEmpty(GeneralRentVehicleRequest request) {
        return validateStringNotEmpty("Email", request.getEmail());
    }

    private Optional<CoreError> validateEmailFormat(GeneralRentVehicleRequest request) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(request.getEmail())
                ? Optional.empty()
                : Optional.of(new CoreError("Email", "not valid format (username@domain.com)"));
    }

    private Optional<CoreError> validatePersonalIdNotEmpty(GeneralRentVehicleRequest request) {
        return validateStringNotEmpty("Personal ID", request.getPersonalId());
    }

    private Optional<CoreError> validatePersonalIdFormat(GeneralRentVehicleRequest request) {
        Pattern pattern = Pattern.compile("[0-9]{6}-[0-9]{5}");
        Matcher matcher = pattern.matcher(request.getPersonalId());
        return !matcher.matches()
                ? Optional.of(new CoreError("Personal ID", "Has to be in format 000000-00000"))
                : Optional.empty();
    }

    private Optional<CoreError> validateFirstNameNotEmpty(GeneralRentVehicleRequest request) {
        return validateStringNotEmpty("First name", request.getFirstName());
    }

    private Optional<CoreError> validateFirstNameFormat(GeneralRentVehicleRequest request) {
        String firstName = request.getFirstName().replaceAll("[a-zA-Z-\s]", "");
        return !firstName.isBlank()
                ? Optional.of(new CoreError("First name", "cannot contain digits or special characters"))
                : Optional.empty();
    }

    private Optional<CoreError> validateLastNameNotEmpty(GeneralRentVehicleRequest request) {
        return validateStringNotEmpty("Last name", request.getLastName());
    }

    private Optional<CoreError> validateLastNameFormat(GeneralRentVehicleRequest request) {
        String lastName = request.getLastName().replaceAll("[a-zA-Z-]", "");
        return !lastName.isBlank()
                ? Optional.of(new CoreError("Last name", "cannot contain digits or special characters"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePhoneNumberNotEmpty(GeneralRentVehicleRequest request) {
        return validateStringNotEmpty("Phone number", request.getPhoneNumber());
    }

    private Optional<CoreError> validatePhoneNumberFormat(GeneralRentVehicleRequest request) {
        String phoneNumber = request.getPhoneNumber().replaceAll("[+0-9]","");
        return !phoneNumber.isBlank()
                ? Optional.of(new CoreError("Phone number", "cannot contain letters or special characters"))
                : Optional.empty();
    }

    private Optional<CoreError> validateStringNotEmpty(String field, String value) {
        return (value == null || value.isBlank())
                ? Optional.of(new CoreError(field, "cannot be empty"))
                : Optional.empty();
    }
}
