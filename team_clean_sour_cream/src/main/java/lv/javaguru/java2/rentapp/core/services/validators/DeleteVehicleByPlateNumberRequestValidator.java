package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteVehicleByPlateNumberRequestValidator {

    @Autowired
    private VehicleDatabase vehicleDatabase;

    public List<CoreError> validate(DeleteVehicleByPlateNumberRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateVehicleByPlateNumber(request).ifPresent(errors::add);
        validateVehicleToDeleteExists(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateVehicleByPlateNumber(DeleteVehicleByPlateNumberRequest request) {
        return (request.getPlateNumber() == null || request.getPlateNumber().isBlank())
                ? Optional.of(new CoreError("Plate number", "can`t be empty or blank"))
                : Optional.empty();
    }

    private Optional<CoreError> validateVehicleToDeleteExists(DeleteVehicleByPlateNumberRequest request) {
        if (request.getPlateNumber() != null && !request.getPlateNumber().isBlank()) {
            Optional<Vehicle> vehicleToDeleteOpt = vehicleDatabase.getAllVehicles().stream()
                    .filter(vehicle -> vehicle.getPlateNumber().equals(request.getPlateNumber()))
                    .findFirst();
            if (vehicleToDeleteOpt.isEmpty()) {
                return Optional.of(new CoreError("Plate number", "vehicle with this plate number is not present in database"));
            }
        }
        return Optional.empty();
    }
}


