package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static lv.javaguru.java2.rentapp.enums.VehicleType.*;

@Component
public class AddVehicleValidatorMap {

    private Map<VehicleType, AddVehicleValidator> addVehicleValidatorMap;

    @Autowired
    public AddVehicleValidatorMap(AddPassengerCarValidator addPassengerCarValidator, AddMiniBusValidator addMiniBusValidator,
                                  AddMotorcycleValidator addMotorcycleValidator, AddCarTrailerValidator addCarTrailerValidator) {
        this.addVehicleValidatorMap = new HashMap<>();
        addVehicleValidatorMap.put(PASSENGER_CAR, addPassengerCarValidator);
        addVehicleValidatorMap.put(MINIBUS, addMiniBusValidator);
        addVehicleValidatorMap.put(MOTORCYCLE, addMotorcycleValidator);
        addVehicleValidatorMap.put(CAR_TRAILER, addCarTrailerValidator);
    }

    public AddVehicleValidator getVehicleValidatorByCarType(VehicleType vehicleType) {
        return addVehicleValidatorMap.get(vehicleType);
    }
}