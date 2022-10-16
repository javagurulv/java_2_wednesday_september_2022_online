package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.database.VehicleDatabaseImpl;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.MotorcycleCreator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static lv.javaguru.java2.rentapp.domain.Motorcycle.MOTO_MAX_PASSENGER_AMOUNT;
import static lv.javaguru.java2.rentapp.domain.Motorcycle.MOTO_MIN_PASSENGER_AMOUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddMotorcycleValidatorTest {

    AddMotorcycleValidator validator;
    VehicleDatabase vehicleDatabase;

    @BeforeEach
    void setUp() {
        vehicleDatabase = new VehicleDatabaseImpl();
        validator = new AddMotorcycleValidator(vehicleDatabase);
    }

    @Test
    void testValidateReturnsEmptyList() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(MOTO_MAX_PASSENGER_AMOUNT).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    void testValidateVehicleIsNotDuplicateShouldReturnNoErrors() {
        AddVehicleRequest request1 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(MOTO_MAX_PASSENGER_AMOUNT).build();
        Vehicle motorcycle1 = new MotorcycleCreator().createVehicle(request1);
        vehicleDatabase.addNewVehicle(motorcycle1);
        AddVehicleRequest request2 = AddVehicleRequest.builder().brand("brand2").model("model2").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(MOTO_MAX_PASSENGER_AMOUNT).build();
        List<CoreError> error = validator.validate(request2);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateVehicleIsNotDuplicateShouldReturnError() {
        AddVehicleRequest request1 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(MOTO_MAX_PASSENGER_AMOUNT).build();
        Vehicle motorcycle1 = new MotorcycleCreator().createVehicle(request1);
        vehicleDatabase.addNewVehicle(motorcycle1);
        AddVehicleRequest request2 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(MOTO_MAX_PASSENGER_AMOUNT).build();
        List<CoreError> errors = validator.validate(request2);
        assertEquals(1, errors.size());
        assertEquals("Vehicle", errors.get(0).getField());
        assertEquals("is already in the database", errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountShouldReturnNoErrors() {
        Integer passengerAmount = MOTO_MAX_PASSENGER_AMOUNT;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(passengerAmount).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidatePassengerAmountIsZeroShouldReturnError() {
        Integer passengerAmount = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(passengerAmount).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + MOTO_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountNegativeShouldReturnError() {
        Integer passengerAmount = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(passengerAmount).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + MOTO_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(null).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + MOTO_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountMoreThanMaxAllowedShouldReturnError() {
        Integer passengerAmount = MOTO_MAX_PASSENGER_AMOUNT + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(passengerAmount).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Passenger amount");
        assertEquals(errors.get(0).getMessage(), "cannot be more than " + MOTO_MAX_PASSENGER_AMOUNT);
    }

    @Test
    void testValidatePassengerAmountLessThanMinAllowedShouldReturnError() {
        Integer passengerAmount = MOTO_MIN_PASSENGER_AMOUNT - 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(passengerAmount).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Passenger amount");
        assertEquals(errors.get(0).getMessage(), "cannot be empty, negative, zero or less than " + MOTO_MIN_PASSENGER_AMOUNT);
    }
}