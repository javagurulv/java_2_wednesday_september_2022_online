package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.MiniBusCreator;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.PassengerCarCreator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddMiniBusValidator.*;
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
    void testValidateReturnsEmptyList() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
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
        List<CoreError> error = validator.validate(request2);
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
        List<CoreError> error = validator.validate(request2);
        assertEquals(1, error.size());
        assertEquals("Vehicle", error.get(0).getField());
        assertEquals("is already in the database", error.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountShouldReturnNoErrors() {
        Integer passengerAmount = MAX_PASSENGER_AMOUNT;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(passengerAmount)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidatePassengerAmountIsZeroShouldReturnError() {
        Integer passengerAmount = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(passengerAmount)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountNegativeShouldReturnError() {
        Integer passengerAmount = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(passengerAmount)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(null)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountMoreThanMaxAllowedShouldReturnError() {
        Integer passengerAmount = MAX_PASSENGER_AMOUNT + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(passengerAmount)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("cannot be more than " + MAX_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateBaggageAmountShouldReturnNoErrors() {
        Integer baggageAmount = MAX_BAGGAGE_AMOUNT;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(baggageAmount).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateBaggageAmountNegativeShouldReturnError() {
        Integer baggageAmount = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(baggageAmount).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Baggage amount", errors.get(0).getField());
        assertEquals("cannot be empty or negative", errors.get(0).getMessage());
    }

    @Test
    void testValidateBaggageAmountIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(null).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Baggage amount", errors.get(0).getField());
        assertEquals("cannot be empty or negative", errors.get(0).getMessage());
    }

    @Test
    void testValidateBaggageAmountMoreThanMaxAllowedShouldReturnError() {
        Integer baggageAmount = MAX_BAGGAGE_AMOUNT + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(baggageAmount).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Baggage amount", errors.get(0).getField());
        assertEquals("cannot be more than " + MAX_BAGGAGE_AMOUNT, errors.get(0).getMessage());
    }


    @Test
    void testValidateDoorsAmountShouldReturnNoErrors() {
        Integer doorsAmount = MAX_DOORS_AMOUNT;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(doorsAmount).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDoorsAmountMoreThanMaxAllowedShouldReturnError() {
        Integer doorsAmount = MAX_DOORS_AMOUNT + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(doorsAmount).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("cannot be more than " + MAX_DOORS_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateDoorsAmountIsZeroShouldReturnError() {
        Integer doorsAmount = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(doorsAmount).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateDoorsAmountNegativeShouldReturnError() {
        Integer doorsAmount = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(doorsAmount).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateDoorsAmountIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(null).isAirConditioningAvailable("true").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateIsAirConditioningAvailableTrueShouldReturnNoErrorsV1() {
        String isAirConditioningAvailable = "true";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable(isAirConditioningAvailable).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateIsAirConditioningAvailableFalseShouldReturnNoErrorsV1() {
        String isAirConditioningAvailable = "false";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable(isAirConditioningAvailable).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateIsAirConditioningAvailableIsEmptyShouldReturnErrorV1() {
        String isAirConditioningAvailable = "";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable(isAirConditioningAvailable).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("IsAirConditionerAvailable", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateIsAirConditioningAvailableIsEmptyShouldReturnErrorV2() {
        String isAirConditioningAvailable = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable(isAirConditioningAvailable).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("IsAirConditionerAvailable", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateIsAirConditioningAvailableIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable(null).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("IsAirConditionerAvailable", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateIsAirConditioningAvailableWrongInputShouldReturnError() {
        String isAirConditioningAvailable = "wrong input";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas")
                .plateNumber("number1").transmissionType("manual").passengerAmount(MAX_PASSENGER_AMOUNT)
                .baggageAmount(MAX_BAGGAGE_AMOUNT).doorsAmount(MAX_DOORS_AMOUNT).isAirConditioningAvailable(isAirConditioningAvailable).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1 , errors.size());
        assertEquals("IsAirConditionerAvailable", errors.get(0).getField());
        assertEquals("must be either true or false", errors.get(0).getMessage());
    }
}