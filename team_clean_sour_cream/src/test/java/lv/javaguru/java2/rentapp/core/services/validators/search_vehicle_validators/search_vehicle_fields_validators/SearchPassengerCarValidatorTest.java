package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchPassengerCarValidatorTest {

    SearchPassengerCarValidator searchPassengerCarValidator;

    SearchVehicleRequest searchVehicleRequest;

    @BeforeEach
    void setUp() {
        searchPassengerCarValidator = new SearchPassengerCarValidator();
    }

    @Test
    void testValidateReturnListWithAllErrorsWhenRequestIsNull() {
        searchVehicleRequest = SearchVehicleRequest.builder()
                .transmissionType("")
                .hasConditioner("")
                .passengerAmount(-1)
                .doorsAmount(-1)
                .baggageAmount(-1)
                .build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertEquals(6, errors.size());
    }

    @Test
    void testValidateReturnListWithNoErrorsWhenRequestIsValid() {
        searchVehicleRequest = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.PASSENGER_CAR)
                .transmissionType("manual")
                .hasConditioner("true")
                .passengerAmount(2)
                .doorsAmount(2)
                .baggageAmount(2)
                .build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateTransmissionTypeIsNullReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(null).build();
        Optional<CoreError> error = searchPassengerCarValidator.validateVehicleType(searchVehicleRequest);
        assertTrue(error.isPresent());
        assertEquals("Vehicle Type", error.get().getField());
        assertEquals("can`t be empty", error.get().getMessage());
    }

}