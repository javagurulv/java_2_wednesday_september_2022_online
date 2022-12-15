package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.VehicleAvailabilityService;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RentVehicleValidator {

    @Autowired
    private VehicleAvailabilityService vehicleAvailabilityService;

    public List<CoreError> validate(GeneralRentVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateIdNotEmpty(request).ifPresent(errors::add);
        if (validateIdNotEmpty(request).isEmpty()) {
            validateChosenIdIsAmongProvided(request).ifPresent(errors::add);
            if (validateChosenIdIsAmongProvided(request).isEmpty()) {
                validateVehicleIsStillAvailable(request).ifPresent(errors::add);
            }
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

}
