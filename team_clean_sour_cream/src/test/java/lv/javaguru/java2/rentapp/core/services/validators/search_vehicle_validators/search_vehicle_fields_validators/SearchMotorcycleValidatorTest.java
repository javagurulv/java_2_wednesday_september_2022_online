package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lv.javaguru.java2.rentapp.domain.Motorcycle.MOTO_MAX_PASSENGER_AMOUNT;
import static lv.javaguru.java2.rentapp.domain.Motorcycle.MOTO_MIN_PASSENGER_AMOUNT;
import static org.junit.jupiter.api.Assertions.*;

class SearchMotorcycleValidatorTest {

    SearchMotorcycleValidator searchMotorcycleValidator;

    SearchVehicleRequest searchVehicleRequest;

    @BeforeEach
    void setUp() {
        searchMotorcycleValidator = new SearchMotorcycleValidator();
    }

    @Test
    void testValidateReturnListWithErrorWhenRequestIsNull() {
        searchVehicleRequest = SearchVehicleRequest.builder().build();
        List<CoreError> errors = searchMotorcycleValidator.validate(searchVehicleRequest);
        assertEquals(1, errors.size());
        assertEquals("Vehicle Type", errors.get(0).getField());
        assertEquals("can`t be null (should be provided)", errors.get(0).getMessage());
    }

    @Test
    void testValidateReturnListWithAllErrorsWhenRequestIsNotValid() {
        searchVehicleRequest = SearchVehicleRequest.builder()
                .transmissionType("")
                .passengerAmount(-1)
                .build();
        List<CoreError> errors = searchMotorcycleValidator.validate(searchVehicleRequest);
        assertEquals(3, errors.size());
    }

    @Test
    void testValidateReturnListWithNoErrorsWhenRequestIsValid() {
        searchVehicleRequest = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.MOTORCYCLE)
                .transmissionType("manual")
                .passengerAmount(2)
                .build();
        List<CoreError> errors = searchMotorcycleValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidatePassengerAmountIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MOTORCYCLE).passengerAmount(1).build();
        List<CoreError> errors = searchMotorcycleValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidatePassengerAmountNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MOTORCYCLE).passengerAmount(null).build();
        List<CoreError> errors = searchMotorcycleValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidatePassengerAmountIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MOTORCYCLE).passengerAmount(-1).build();
        List<CoreError> errors = searchMotorcycleValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + MOTO_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountIsZeroReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MOTORCYCLE).passengerAmount(0).build();
        List<CoreError> errors = searchMotorcycleValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + MOTO_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountIsLessThanMinAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MOTORCYCLE).passengerAmount(MOTO_MIN_PASSENGER_AMOUNT - 1).build();
        List<CoreError> errors = searchMotorcycleValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + MOTO_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MOTORCYCLE).passengerAmount(MOTO_MAX_PASSENGER_AMOUNT + 1).build();
        List<CoreError> errors = searchMotorcycleValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be more than " + MOTO_MAX_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

}