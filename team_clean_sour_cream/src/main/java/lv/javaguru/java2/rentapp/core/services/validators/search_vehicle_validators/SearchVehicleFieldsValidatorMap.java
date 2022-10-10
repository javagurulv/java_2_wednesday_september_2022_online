package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

import static lv.javaguru.java2.rentapp.enums.VehicleType.*;

public class SearchVehicleFieldsValidatorMap {

    private Map<VehicleType, SearchVehicleFieldsValidator> searchVehicleValidatorMap;

    public SearchVehicleFieldsValidatorMap() {
        this.searchVehicleValidatorMap = new HashMap<>();
        searchVehicleValidatorMap.put(PASSENGER_CAR, new SearchPassengerCarValidator());
        searchVehicleValidatorMap.put(MINIBUS, new SearchMiniBusValidator());
        searchVehicleValidatorMap.put(MOTORCYCLE, new SearchMotorcycleValidator());
        searchVehicleValidatorMap.put(CAR_TRAILER, new SearchCarTrailerValidator());
    }

    public SearchVehicleFieldsValidator getVehicleValidatorByCarType(VehicleType vehicleType) {
        return searchVehicleValidatorMap.get(vehicleType);
    }
}
