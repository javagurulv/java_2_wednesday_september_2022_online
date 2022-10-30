package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;

import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static lv.javaguru.java2.rentapp.enums.VehicleType.*;
import static lv.javaguru.java2.rentapp.enums.VehicleType.CAR_TRAILER;

@Component
public class VehicleCreatorMap {

    private Map<VehicleType, VehicleCreator> vehicleTypeCreatorMap;

    @Autowired
    public VehicleCreatorMap(PassengerCarCreator passengerCarCreator, MiniBusCreator miniBusCreator,
                             MotorcycleCreator motorcycleCreator, CarTrailerCreator carTrailerCreator) {
        this.vehicleTypeCreatorMap = new HashMap<>();
        vehicleTypeCreatorMap.put(PASSENGER_CAR, passengerCarCreator);
        vehicleTypeCreatorMap.put(MINIBUS, miniBusCreator);
        vehicleTypeCreatorMap.put(MOTORCYCLE, motorcycleCreator);
        vehicleTypeCreatorMap.put(CAR_TRAILER, carTrailerCreator);
    }

    public VehicleCreator getVehicleTypeCreatorByCarType(VehicleType vehicleType) {
        return vehicleTypeCreatorMap.get(vehicleType);
    }
}
