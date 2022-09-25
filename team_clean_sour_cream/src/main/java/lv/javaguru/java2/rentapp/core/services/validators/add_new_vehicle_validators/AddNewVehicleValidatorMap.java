package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

import static lv.javaguru.java2.rentapp.enums.VehicleType.PASSENGER_CAR;

public class AddNewVehicleValidatorMap {

    private Map<VehicleType, AddNewVehicleValidator> addNewVehicleValidatorMap;

    public AddNewVehicleValidatorMap() {
        this.addNewVehicleValidatorMap = new HashMap<>();
        addNewVehicleValidatorMap.put(PASSENGER_CAR, new AddNewPassengerCarValidator());
    }

    public AddNewVehicleValidator getVehicleValidatorByCarType(VehicleType vehicleType) {
        return addNewVehicleValidatorMap.get(vehicleType);
    }
}