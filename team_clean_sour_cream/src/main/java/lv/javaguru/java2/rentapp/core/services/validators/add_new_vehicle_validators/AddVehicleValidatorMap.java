package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

import static lv.javaguru.java2.rentapp.enums.VehicleType.*;

public class AddVehicleValidatorMap {

    private Map<VehicleType, AddVehicleValidator> addVehicleValidatorMap;

    public AddVehicleValidatorMap(Database database) {
        this.addVehicleValidatorMap = new HashMap<>();
        addVehicleValidatorMap.put(PASSENGER_CAR, new AddPassengerCarValidator(database));
        addVehicleValidatorMap.put(MINIBUS, new AddMiniBusValidator(database));
        addVehicleValidatorMap.put(MOTORCYCLE, new AddMotorcycleValidator(database));
        addVehicleValidatorMap.put(CAR_TRAILER, new AddCarTrailerValidator(database));
    }

    public AddVehicleValidator getVehicleValidatorByCarType(VehicleType vehicleType) {
        return addVehicleValidatorMap.get(vehicleType);
    }
}