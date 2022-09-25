package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddMiniBusValidatorTest {

    AddMiniBusValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AddMiniBusValidator();
    }

    @Test
    void testValidateReturnsListOfAllErrors() {
        AddVehicleRequest request = AddVehicleRequest.builder().build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 12);
    }

    @Test
    void testValidatePassengerAmountShouldReturnNoErrors() {
        Integer passengerAmount = 5;
        AddVehicleRequest request = AddVehicleRequest.builder().passengerAmount(passengerAmount).build();
        Optional<CoreError> errorOptional = validator.validatePassengerAmount(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidatePassengerAmountShouldReturnErrorV1() {
        Integer passengerAmount = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().passengerAmount(passengerAmount).build();
        Optional<CoreError> errorOptional = validator.validatePassengerAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Passenger amount");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty, negative or 0");
    }

    @Test
    void testValidatePassengerAmountShouldReturnErrorV2() {
        Integer passengerAmount = 1000;
        AddVehicleRequest request = AddVehicleRequest.builder().passengerAmount(passengerAmount).build();
        Optional<CoreError> errorOptional = validator.validatePassengerAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Passenger amount");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + AddMiniBusValidator.MAX_PASSENGER_AMOUNT);
    }

    @Test
    void testValidateBaggageAmountShouldReturnNoErrors() {
        Integer baggageAmount = 5;
        AddVehicleRequest request = AddVehicleRequest.builder().baggageAmount(baggageAmount).build();
        Optional<CoreError> errorOptional = validator.validateBaggageAmount(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateBaggageAmountShouldReturnErrorV1() {
        Integer baggageAmount = -10;
        AddVehicleRequest request = AddVehicleRequest.builder().baggageAmount(baggageAmount).build();
        Optional<CoreError> errorOptional = validator.validateBaggageAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Baggage amount");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty or negative");
    }

    @Test
    void testValidateBaggageAmountShouldReturnErrorV2() {
        Integer baggageAmount = 1000;
        AddVehicleRequest request = AddVehicleRequest.builder().baggageAmount(baggageAmount).build();
        Optional<CoreError> errorOptional = validator.validateBaggageAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Baggage amount");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + AddMiniBusValidator.MAX_BAGGAGE_AMOUNT);
    }

    @Test
    void testValidateDoorsAmountShouldReturnErrorV2() {
        Integer doorsAmount = 11;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Doors amount");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + AddMiniBusValidator.MAX_DOORS_AMOUNT);
    }

    @Test
    void testValidateDoorsAmountShouldReturnNoErrors() {
        Integer doorsAmount = 4;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateDoorsAmountShouldReturnErrorV1() {
        Integer doorsAmount = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Doors amount");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty, negative or 0");
    }

    @Test
    void testValidateIsAirConditioningAvailableShouldReturnNoErrors() {
        String isAirConditioningAvailable = "true";
        AddVehicleRequest request = AddVehicleRequest.builder().isAirConditioningAvailable(isAirConditioningAvailable).build();
        Optional<CoreError> errorOptional = validator.validateIsAirConditionerAvailable(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateIsAirConditioningAvailableShouldReturnErrorV1() {
        String isAirConditioningAvailable = "";
        AddVehicleRequest request = AddVehicleRequest.builder().isAirConditioningAvailable(isAirConditioningAvailable).build();
        Optional<CoreError> errorOptional = validator.validateIsAirConditionerAvailable(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "IsAirConditionerAvailable");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty");
    }

    @Test
    void testValidateIsAirConditioningAvailableShouldReturnErrorV2() {
        String isAirConditioningAvailable = "wrong input";
        AddVehicleRequest request = AddVehicleRequest.builder().isAirConditioningAvailable(isAirConditioningAvailable).build();
        Optional<CoreError> errorOptional = validator.validateIsAirConditionerAvailable(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "IsAirConditionerAvailable");
        assertEquals(errorOptional.get().getMessage(), "must be either true or false");
    }
}