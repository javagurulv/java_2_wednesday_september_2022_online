package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SearchVehicleValidatorTest {

    SearchVehicleFieldsValidator searchVehicleValidator;

    @BeforeEach
    void setup() {
        searchVehicleValidator = new SearchPassengerCarValidator();
    }


    @Test
    void validateVehicleType() {

        SearchVehicleRequest request = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).build();
        Optional<CoreError> coreError = searchVehicleValidator.validateVehicleType(request);
        assertTrue(coreError.isEmpty());
    }
}