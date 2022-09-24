package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddNewVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddNewVehicleResponse;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.*;
import lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddNewVehicleValidator;
import lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddNewVehicleValidatorMap;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.List;

public class AddNewVehicleService {

    private VehicleTypeCreatorMap vehicleTypeCreatorMap;
    private AddNewVehicleValidatorMap addNewVehicleValidatorMap;

    public AddNewVehicleService(Database database, AddNewVehicleValidator addNewVehicleValidator) {
        this.vehicleTypeCreatorMap = new VehicleTypeCreatorMap(database);
        this.addNewVehicleValidatorMap = new AddNewVehicleValidatorMap();
    }

    public AddNewVehicleResponse execute(AddNewVehicleRequest request) {
        VehicleType vehicleType = request.getVehicleType();
        AddNewVehicleValidator addNewVehicleValidator = addNewVehicleValidatorMap.getVehicleValidatorByCarType(vehicleType);

        List<CoreError> errors = addNewVehicleValidator.validate(request);
        if (!errors.isEmpty()) {
            return new AddNewVehicleResponse(errors);
        }

        VehicleTypeCreator vehicleTypeCreator = vehicleTypeCreatorMap.getVehicleTypeCreatorByCarType(vehicleType);
        return vehicleTypeCreator.createVehicle(request);
    }
}
