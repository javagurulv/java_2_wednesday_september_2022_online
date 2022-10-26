package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

import static lv.javaguru.java2.rentapp.enums.VehicleType.*;

public class AddVehicleValidatorMap {

    private Map<VehicleType, AddVehicleValidator> addVehicleValidatorMap;

    public AddVehicleValidatorMap(VehicleDatabase vehicleDatabase) {
        this.addVehicleValidatorMap = new HashMap<>();
        addVehicleValidatorMap.put(PASSENGER_CAR, new AddPassengerCarValidator(vehicleDatabase));
        addVehicleValidatorMap.put(MINIBUS, new AddMiniBusValidator(vehicleDatabase));
        addVehicleValidatorMap.put(MOTORCYCLE, new AddMotorcycleValidator(vehicleDatabase));
        addVehicleValidatorMap.put(CAR_TRAILER, new AddCarTrailerValidator(vehicleDatabase));
    }

    public AddVehicleValidator getVehicleValidatorByCarType(VehicleType vehicleType) {
        return addVehicleValidatorMap.get(vehicleType);
    }
}