package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

import static lv.javaguru.java2.rentapp.enums.VehicleType.*;
import static lv.javaguru.java2.rentapp.enums.VehicleType.CAR_TRAILER;

public class VehicleCreatorMap {

    private Map<VehicleType, VehicleCreator> vehicleTypeCreatorMap;

    public VehicleCreatorMap(Database database) {
        this.vehicleTypeCreatorMap = new HashMap<>();
        vehicleTypeCreatorMap.put(PASSENGER_CAR, new PassengerCarCreator(database));
        vehicleTypeCreatorMap.put(MINIBUS, new MiniBusCreator(database));
        vehicleTypeCreatorMap.put(MOTORCYCLE, new MotorcycleCreator(database));
        vehicleTypeCreatorMap.put(CAR_TRAILER, new CarTrailerCreator(database));
    }

    public VehicleCreator getVehicleTypeCreatorByCarType(VehicleType vehicleType) {
        return vehicleTypeCreatorMap.get(vehicleType);
    }
}
