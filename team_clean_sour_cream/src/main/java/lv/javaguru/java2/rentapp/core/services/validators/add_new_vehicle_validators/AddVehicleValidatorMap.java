package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

import static lv.javaguru.java2.rentapp.enums.VehicleType.*;

public class AddVehicleValidatorMap {

    private Map<VehicleType, AddVehicleValidator> addVehicleValidatorMap;

    public AddVehicleValidatorMap() {
        this.addVehicleValidatorMap = new HashMap<>();
        addVehicleValidatorMap.put(PASSENGER_CAR, new AddPassengerCarValidator());
        addVehicleValidatorMap.put(MINIBUS, new AddMiniBusValidator());
        addVehicleValidatorMap.put(MOTORCYCLE, new AddMotorcycleValidator());
        addVehicleValidatorMap.put(CAR_TRAILER, new AddCarTrailerValidator());
    }

    public AddVehicleValidator getVehicleValidatorByCarType(VehicleType vehicleType) {
        return addVehicleValidatorMap.get(vehicleType);
    }
}