package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteVehicleByPlateNumberValidator {

    Database database;

    public List<CoreError> validate(DeleteVehicleByPlateNumberRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateVehiclePlateNumber(request).ifPresent(errors::add);
        return errors;
    }

    public Optional<CoreError> validateVehiclePlateNumber(DeleteVehicleByPlateNumberRequest request) {
        String plateNumber = request.getPlateNumber();
        if (plateNumber == null || plateNumber.isEmpty()) {
            return Optional.of(new CoreError("Plate number", "cannot be empty"));
        } else {
            return Optional.empty();
        }
    }
}


