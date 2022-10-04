package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.search_vehicle_request_creators.SearchPassengerCarRequestCreator;
import lv.javaguru.java2.rentapp.core.requests.search_vehicle_request_creators.SearchVehicleRequestCreator;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SearchVehicleValidatorTest {

    SearchVehicleValidator searchVehicleValidator;
    SearchVehicleRequest searchVehicleRequest;
    SearchVehicleRequestCreator searchVehicleRequestCreator;


    @Test
    void validateVehicleType() {
        searchVehicleRequestCreator = new SearchPassengerCarRequestCreator();
        SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR);
        searchVehicleRequest = searchVehicleRequestBuilder.build();
        searchVehicleValidator = new SearchPassengerCarValidator();
        Optional<CoreError> coreError = searchVehicleValidator.validateVehicleType(searchVehicleRequest);
        assertTrue(coreError.isEmpty());
    }
}