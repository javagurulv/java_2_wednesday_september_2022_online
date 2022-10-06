package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteVehicleByPlateNumberValidator {

    private Database database;

    public DeleteVehicleByPlateNumberValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(DeleteVehicleByPlateNumberRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateVehicleByPlateNumber(request).ifPresent(errors::add);
        validateVehicleToDeleteExists(request).ifPresent(errors::add);
        return errors;
    }

    protected Optional<CoreError> validateVehicleByPlateNumber(DeleteVehicleByPlateNumberRequest request) {
        return (request.getPlateNumber() == null || request.getPlateNumber().isBlank())
                ? Optional.of(new CoreError("Plate number", "can`t be empty or blank"))
                : Optional.empty();
    }

    private Optional<CoreError> validateVehicleToDeleteExists(DeleteVehicleByPlateNumberRequest request) {
        if (request.getPlateNumber() != null && !request.getPlateNumber().isBlank()) {
            Optional<Vehicle> vehicleToDeleteOpt = database.getAllVehicles().stream().
                    filter(vehicle -> vehicle.getPlateNumber().equals(request.getPlateNumber()))
                    .findFirst();
            if (vehicleToDeleteOpt.isEmpty()) {
                return Optional.of(new CoreError("Plate number", "vehicle with this plate number is not present in database"));
            }
        }
        return Optional.empty();
    }
}


