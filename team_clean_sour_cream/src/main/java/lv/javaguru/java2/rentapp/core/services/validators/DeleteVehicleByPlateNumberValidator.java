package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteVehicleByPlateNumberValidator {

    public List<CoreError> validate(DeleteVehicleByPlateNumberRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateVehicleByPlateNumber(request).ifPresent(errors::add);
        return errors;
    }

    public Optional<CoreError> validateVehicleByPlateNumber(DeleteVehicleByPlateNumberRequest request) {
        return (request.getPlateNumber() == null)
                ? Optional.of(new CoreError("Plate number", "cannot be empty"))
                : Optional.empty();
    }
}


