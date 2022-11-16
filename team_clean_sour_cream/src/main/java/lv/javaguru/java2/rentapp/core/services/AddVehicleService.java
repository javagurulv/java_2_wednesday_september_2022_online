package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.*;
import lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators.AddVehicleValidator;
import lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators.AddVehicleValidatorMap;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddVehicleService {
    @Autowired
    private VehicleCreatorMap vehicleCreatorMap;
    @Autowired
    private AddVehicleValidatorMap vehicleValidatorMap;
    @Autowired
    private VehicleDatabase vehicleDatabase;

    public AddVehicleResponse execute(AddVehicleRequest request) {
        VehicleType vehicleType = request.getVehicleType();
        AddVehicleValidator addVehicleValidator = vehicleValidatorMap.getVehicleValidatorByCarType(vehicleType);

        List<CoreError> errors = addVehicleValidator.validate(request);
        if (!errors.isEmpty()) {
            return new AddVehicleResponse(errors);
        }

        VehicleCreator vehicleTypeCreator = vehicleCreatorMap.getVehicleTypeCreatorByCarType(vehicleType);
        Vehicle vehicle = vehicleTypeCreator.createVehicle(request);
        vehicleDatabase.addNewVehicle(vehicle);
        return new AddVehicleResponse(vehicle);
    }
}
