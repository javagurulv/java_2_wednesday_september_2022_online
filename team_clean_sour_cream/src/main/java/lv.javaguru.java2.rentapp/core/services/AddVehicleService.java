package lv.javaguru.java2.rentapp.core.services;

import static lv.javaguru.java2.rentapp.enums.VehicleType.CAR_TRAILER;
import static lv.javaguru.java2.rentapp.enums.VehicleType.MINIBUS;
import static lv.javaguru.java2.rentapp.enums.VehicleType.MOTORCYCLE;
import static lv.javaguru.java2.rentapp.enums.VehicleType.PASSENGER_CAR;

import java.util.HashMap;
import java.util.Map;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.vehicle_creators.*;
import lv.javaguru.java2.rentapp.enums.VehicleType;

public class AddVehicleService {

    private Map<VehicleType, VehicleTypeCreator> vehicleTypeCreatorMap;

    public AddVehicleService(Database database) {
        this.vehicleTypeCreatorMap = new HashMap<>();
        vehicleTypeCreatorMap.put(PASSENGER_CAR, new PassengerCarCreator(database));
        vehicleTypeCreatorMap.put(MINIBUS, new MiniBusCreator(database));
        vehicleTypeCreatorMap.put(MOTORCYCLE, new MotorcycleCreator(database));
        vehicleTypeCreatorMap.put(CAR_TRAILER, new CarTrailerCreator(database));
    }

    public AddVehicleResponse execute(AddVehicleRequest request) {
        VehicleType vehicleType = request.getVehicleType();
        VehicleTypeCreator creator = vehicleTypeCreatorMap.get(vehicleType);
        return creator.create(request);
    }

}
