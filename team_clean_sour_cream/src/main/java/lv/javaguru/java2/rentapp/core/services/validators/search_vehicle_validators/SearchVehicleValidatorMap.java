package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

import static lv.javaguru.java2.rentapp.enums.VehicleType.*;

public class SearchVehicleValidatorMap {

    private Map<VehicleType, SearchVehicleValidator> searchVehicleValidatorMap;

    public SearchVehicleValidatorMap() {
        this.searchVehicleValidatorMap = new HashMap<>();
        searchVehicleValidatorMap.put(PASSENGER_CAR, new SearchPassengerCarValidator());
        searchVehicleValidatorMap.put(MINIBUS, new SearchMiniBusValidator());
        searchVehicleValidatorMap.put(MOTORCYCLE, new SearchMotorcycleValidator());
        searchVehicleValidatorMap.put(CAR_TRAILER, new SearchCarTrailerValidator());
    }

    public SearchVehicleValidator getVehicleValidatorByCarType(VehicleType vehicleType) {
        return searchVehicleValidatorMap.get(vehicleType);
    }
}
