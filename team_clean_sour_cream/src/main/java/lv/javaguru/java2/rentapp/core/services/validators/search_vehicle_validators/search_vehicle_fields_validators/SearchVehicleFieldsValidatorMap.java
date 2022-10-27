package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators;

import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static lv.javaguru.java2.rentapp.enums.VehicleType.*;

@Component
public class SearchVehicleFieldsValidatorMap {

    private Map<VehicleType, SearchVehicleFieldsValidator> searchVehicleValidatorMap;

    @Autowired
    public SearchVehicleFieldsValidatorMap(SearchPassengerCarValidator searchPassengerCarValidator, SearchMiniBusValidator searchMiniBusValidator,
                                           SearchMotorcycleValidator searchMotorcycleValidator, SearchCarTrailerValidator searchCarTrailerValidator) {
        this.searchVehicleValidatorMap = new HashMap<>();
        searchVehicleValidatorMap.put(PASSENGER_CAR, searchPassengerCarValidator);
        searchVehicleValidatorMap.put(MINIBUS, searchMiniBusValidator);
        searchVehicleValidatorMap.put(MOTORCYCLE, searchMotorcycleValidator);
        searchVehicleValidatorMap.put(CAR_TRAILER, searchCarTrailerValidator);
    }

    public SearchVehicleFieldsValidator getVehicleValidatorByCarType(VehicleType vehicleType) {
        return searchVehicleValidatorMap.get(vehicleType);
    }
}
