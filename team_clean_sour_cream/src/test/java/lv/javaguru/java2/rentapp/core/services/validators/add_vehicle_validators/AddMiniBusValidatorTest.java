package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.MiniBusCreator;
import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddMiniBusValidatorTest {

    AddMiniBusValidator validator;
    Database database;

    @BeforeEach
    void setUp() {
        database = new InMemoryDatabaseImpl();
        validator = new AddMiniBusValidator(database);
    }

    @Test
    void testValidateReturnsListOfAllErrors() {
        AddVehicleRequest request = AddVehicleRequest.builder().build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 12);
    }

    @Test
    void testValidateVehicleIsNotDuplicateShouldReturnNoErrors() {
        AddVehicleRequest request1 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(1).baggageAmount(1).doorsAmount(1).isAirConditioningAvailable("true").build();
        Vehicle miniBss1 = new MiniBusCreator().createVehicle(request1);
        database.addNewVehicle(miniBss1);
        AddVehicleRequest request2 = AddVehicleRequest.builder().brand("brand2").model("model2").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(1).baggageAmount(1).doorsAmount(1).isAirConditioningAvailable("true").build();
        Optional<CoreError> error = validator.validateVehicleIsNotDuplicate(request2);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateVehicleIsNotDuplicateShouldReturnError() {
        AddVehicleRequest request1 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(1).baggageAmount(1).doorsAmount(1).isAirConditioningAvailable("true").build();
        Vehicle miniBus1 = new MiniBusCreator().createVehicle(request1);
        database.addNewVehicle(miniBus1);
        AddVehicleRequest request2 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(1).baggageAmount(1).doorsAmount(1).isAirConditioningAvailable("true").build();
        Optional<CoreError> error = validator.validateVehicleIsNotDuplicate(request2);
        assertTrue(error.isPresent());
        assertEquals("Vehicle", error.get().getField());
        assertEquals("is already in the database", error.get().getMessage());
    }

    @Test
    void testValidatePassengerAmountShouldReturnNoErrors() {
        Integer passengerAmount = MiniBus.BUS_MAX_PASSENGER_AMOUNT;
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
        Integer passengerAmount = MiniBus.BUS_MAX_PASSENGER_AMOUNT + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().passengerAmount(passengerAmount).build();
        Optional<CoreError> errorOptional = validator.validatePassengerAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Passenger amount", errorOptional.get().getField());
        assertEquals("cannot be more than " + MiniBus.BUS_MAX_PASSENGER_AMOUNT, errorOptional.get().getMessage());
    }

    @Test
    void testValidateBaggageAmountShouldReturnNoErrors() {
        Integer baggageAmount = MiniBus.BUS_MAX_BAGGAGE_AMOUNT;
        AddVehicleRequest request = AddVehicleRequest.builder().baggageAmount(baggageAmount).build();
        Optional<CoreError> errorOptional = validator.validateBaggageAmount(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateBaggageAmountNegativeShouldReturnError() {
        Integer baggageAmount = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().baggageAmount(baggageAmount).build();
        Optional<CoreError> errorOptional = validator.validateBaggageAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Baggage amount", errorOptional.get().getField());
        assertEquals("cannot be empty or negative", errorOptional.get().getMessage());
    }

    @Test
    void testValidateBaggageAmountIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().baggageAmount(null).build();
        Optional<CoreError> errorOptional = validator.validateBaggageAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Baggage amount", errorOptional.get().getField());
        assertEquals("cannot be empty or negative", errorOptional.get().getMessage());
    }

    @Test
    void testValidateBaggageAmountMoreThanMaxAllowedShouldReturnError() {
        Integer baggageAmount = MiniBus.BUS_MAX_BAGGAGE_AMOUNT + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().baggageAmount(baggageAmount).build();
        Optional<CoreError> errorOptional = validator.validateBaggageAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Baggage amount", errorOptional.get().getField());
        assertEquals("cannot be more than " + MiniBus.BUS_MAX_BAGGAGE_AMOUNT, errorOptional.get().getMessage());
    }


    @Test
    void testValidateDoorsAmountShouldReturnNoErrors() {
        Integer doorsAmount = MiniBus.BUS_MAX_DOORS_AMOUNT;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateDoorsAmountMoreThanMaxAllowedShouldReturnError() {
        Integer doorsAmount = MiniBus.BUS_MAX_DOORS_AMOUNT + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Doors amount", errorOptional.get().getField());
        assertEquals("cannot be more than " + MiniBus.BUS_MAX_DOORS_AMOUNT, errorOptional.get().getMessage());
    }

    @Test
    void testValidateDoorsAmountIsZeroShouldReturnError() {
        Integer doorsAmount = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Doors amount", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDoorsAmountNegativeShouldReturnError() {
        Integer doorsAmount = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Doors amount", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDoorsAmountIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(null).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Doors amount", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateIsAirConditioningAvailableTrueShouldReturnNoErrorsV1() {
        String isAirConditioningAvailable = "true";
        AddVehicleRequest request = AddVehicleRequest.builder().isAirConditioningAvailable(isAirConditioningAvailable).build();
        Optional<CoreError> errorOptional = validator.validateIsAirConditionerAvailable(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateIsAirConditioningAvailableFalseShouldReturnNoErrorsV1() {
        String isAirConditioningAvailable = "false";
        AddVehicleRequest request = AddVehicleRequest.builder().isAirConditioningAvailable(isAirConditioningAvailable).build();
        Optional<CoreError> errorOptional = validator.validateIsAirConditionerAvailable(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateIsAirConditioningAvailableIsEmptyShouldReturnErrorV1() {
        String isAirConditioningAvailable = "";
        AddVehicleRequest request = AddVehicleRequest.builder().isAirConditioningAvailable(isAirConditioningAvailable).build();
        Optional<CoreError> errorOptional = validator.validateIsAirConditionerAvailable(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("IsAirConditionerAvailable", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateIsAirConditioningAvailableIsEmptyShouldReturnErrorV2() {
        String isAirConditioningAvailable = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().isAirConditioningAvailable(isAirConditioningAvailable).build();
        Optional<CoreError> errorOptional = validator.validateIsAirConditionerAvailable(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("IsAirConditionerAvailable", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateIsAirConditioningAvailableIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().isAirConditioningAvailable(null).build();
        Optional<CoreError> errorOptional = validator.validateIsAirConditionerAvailable(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("IsAirConditionerAvailable", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateIsAirConditioningAvailableWrongInputShouldReturnError() {
        String isAirConditioningAvailable = "wrong input";
        AddVehicleRequest request = AddVehicleRequest.builder().isAirConditioningAvailable(isAirConditioningAvailable).build();
        Optional<CoreError> errorOptional = validator.validateIsAirConditionerAvailable(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("IsAirConditionerAvailable", errorOptional.get().getField());
        assertEquals("must be either true or false", errorOptional.get().getMessage());
    }
}