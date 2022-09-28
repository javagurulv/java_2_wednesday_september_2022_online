package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.*;
import lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddVehicleValidator;
import lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddVehicleValidatorMap;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.List;

public class AddVehicleService {

    private VehicleCreatorMap vehicleCreatorMap;
    private AddVehicleValidatorMap vehicleValidatorMap;
    private Database database;

    public AddVehicleService(Database database) {
        this.database = database;
        this.vehicleCreatorMap = new VehicleCreatorMap(database);
        this.vehicleValidatorMap = new AddVehicleValidatorMap(database);
    }

    public AddVehicleResponse execute(AddVehicleRequest request) {
        VehicleType vehicleType = request.getVehicleType();
        AddVehicleValidator addVehicleValidator = vehicleValidatorMap.getVehicleValidatorByCarType(vehicleType);

        List<CoreError> errors = addVehicleValidator.validate(request);
        if (!errors.isEmpty()) {
            return new AddVehicleResponse(errors);
        }

        VehicleCreator vehicleTypeCreator = vehicleCreatorMap.getVehicleTypeCreatorByCarType(vehicleType);
        Vehicle vehicle = vehicleTypeCreator.createVehicle(request);
        return new AddVehicleResponse(database.addNewVehicle(vehicle));
    }
}
