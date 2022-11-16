package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;

import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lv.javaguru.java2.rentapp.enums.VehicleType.*;
import static lv.javaguru.java2.rentapp.enums.VehicleType.CAR_TRAILER;

@Component
public class VehicleCreatorMap {

    private Map<VehicleType, VehicleCreator> vehicleTypeCreatorMap;

    @Autowired
    public VehicleCreatorMap(List<VehicleCreator> vehicleCreators) {
        this.vehicleTypeCreatorMap = new HashMap<>();
        vehicleTypeCreatorMap.put(PASSENGER_CAR, findVehicleCreator(vehicleCreators, PassengerCarCreator.class));
        vehicleTypeCreatorMap.put(MINIBUS, findVehicleCreator(vehicleCreators, MiniBusCreator.class));
        vehicleTypeCreatorMap.put(MOTORCYCLE, findVehicleCreator(vehicleCreators, MotorcycleCreator.class));
        vehicleTypeCreatorMap.put(CAR_TRAILER, findVehicleCreator(vehicleCreators, CarTrailerCreator.class));
    }

    public VehicleCreator getVehicleTypeCreatorByCarType(VehicleType vehicleType) {
        return vehicleTypeCreatorMap.get(vehicleType);
    }

    private VehicleCreator findVehicleCreator(List<VehicleCreator> vehicleCreators, Class vehicleCreatorClass) {
        return vehicleCreators.stream()
                .filter(vehicleCreator -> vehicleCreator.getClass().equals(vehicleCreatorClass))
                .findFirst()
                .get();
    }
}
