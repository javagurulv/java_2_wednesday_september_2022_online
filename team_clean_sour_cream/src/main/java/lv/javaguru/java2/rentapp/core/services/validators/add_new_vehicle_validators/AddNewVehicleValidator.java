package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddNewVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;

import java.util.List;

public interface AddNewVehicleValidator {

    List<CoreError> validate(AddNewVehicleRequest request);

}
