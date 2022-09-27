package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddMotorcycleValidator.MAX_PASSENGER_AMOUNT;
import static org.junit.jupiter.api.Assertions.*;

class AddMotorcycleValidatorTest {

    AddMotorcycleValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AddMotorcycleValidator();
    }

    @Test
    void testValidateReturnsListOfAllErrors() {
        AddVehicleRequest request = AddVehicleRequest.builder().build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 9);
    }

    @Test
    void testValidatePassengerAmountShouldReturnNoErrors() {
        Integer passengerAmount = MAX_PASSENGER_AMOUNT;
        AddVehicleRequest request = AddVehicleRequest.builder().passengerAmount(passengerAmount).build();
        Optional<CoreError> errorOptional = validator.validatePassengerAmount(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidatePassengerAmountIsZeroShouldReturnError() {
        Integer passengerAmount = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().passengerAmount(passengerAmount).build();
        Optional<CoreError> errorOptional = validator.validatePassengerAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Passenger amount", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidatePassengerAmountNegativeShouldReturnError() {
        Integer passengerAmount = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().passengerAmount(passengerAmount).build();
        Optional<CoreError> errorOptional = validator.validatePassengerAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Passenger amount", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidatePassengerAmountIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().passengerAmount(null).build();
        Optional<CoreError> errorOptional = validator.validatePassengerAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Passenger amount", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidatePassengerAmountMoreThanMaxAllowedShouldReturnError() {
        Integer passengerAmount = MAX_PASSENGER_AMOUNT + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().passengerAmount(passengerAmount).build();
        Optional<CoreError> errorOptional = validator.validatePassengerAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Passenger amount");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + MAX_PASSENGER_AMOUNT);
    }
}