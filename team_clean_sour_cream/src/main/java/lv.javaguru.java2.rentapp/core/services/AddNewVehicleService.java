package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddNewVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddNewVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.vehicle_creators.*;
import lv.javaguru.java2.rentapp.enums.VehicleType;

public class AddNewVehicleService {

    private VehicleTypeCreatorMap vehicleTypeCreatorMap;

    public AddNewVehicleService(Database database) {
        this.vehicleTypeCreatorMap = new VehicleTypeCreatorMap(database);
    }

    public AddNewVehicleResponse execute(AddNewVehicleRequest request) {
        VehicleType vehicleType = request.getVehicleType();
        VehicleTypeCreator vehicleTypeCreator = vehicleTypeCreatorMap.getVehicleTypeCreatorByCarType(vehicleType);
        return vehicleTypeCreator.createVehicle(request);
    }
}
