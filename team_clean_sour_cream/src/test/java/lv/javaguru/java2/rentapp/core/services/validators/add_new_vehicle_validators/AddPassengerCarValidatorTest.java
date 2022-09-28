package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.PassengerCarCreator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddPassengerCarValidator.*;
import static lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddVehicleValidator.CURRENT_YEAR_BACKWARD_REDUCER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddPassengerCarValidatorTest {

    AddPassengerCarValidator validator;
    Database database;

    @BeforeEach
    void setUp() {
        database = new InMemoryDatabaseImpl();
        validator = new AddPassengerCarValidator(database);
    }

    @Test
    void testValidateReturnsListOfAllErrors() {
        AddVehicleRequest request = AddVehicleRequest.builder().build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(12, errors.size());
    }

    @Test
    void testValidateVehicleIsNotDuplicateShouldReturnNoErrors() {
        AddVehicleRequest request1 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(1).baggageAmount(1).doorsAmount(1).isAirConditioningAvailable("true").build();
        new PassengerCarCreator(database).createVehicle(request1);
        AddVehicleRequest request2 = AddVehicleRequest.builder().brand("brand2").model("model2").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").passengerAmount(1).baggageAmount(1).doorsAmount(1).isAirConditioningAvailable("true").build();
        Optional<CoreError> error = validator.validateVehicleIsNotDuplicate(request2);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateBrandShouldReturnNoErrors() {
        String brand = "brand";
        AddVehicleRequest request = AddVehicleRequest.builder().brand(brand).build();
        Optional<CoreError> errorOptional = validator.validateBrand(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateBrandIsEmptyShouldReturnErrorV1() {
        String brand = "";
        AddVehicleRequest request = AddVehicleRequest.builder().brand(brand).build();
        Optional<CoreError> errorOptional = validator.validateBrand(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Brand", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateBrandIsEmptyShouldReturnErrorV2() {
        String brand = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().brand(brand).build();
        Optional<CoreError> errorOptional = validator.validateBrand(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Brand", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateBrandIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand(null).build();
        Optional<CoreError> errorOptional = validator.validateBrand(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Brand", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateModelShouldReturnNoErrors() {
        String model = "model";
        AddVehicleRequest request = AddVehicleRequest.builder().model(model).build();
        Optional<CoreError> errorOptional = validator.validateModel(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateModelIsEmptyShouldReturnErrorV1() {
        String model = "";
        AddVehicleRequest request = AddVehicleRequest.builder().model(model).build();
        Optional<CoreError> errorOptional = validator.validateModel(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Model", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateModelIsEmptyShouldReturnErrorV2() {
        String model = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().model(model).build();
        Optional<CoreError> errorOptional = validator.validateModel(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Model", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateModelIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().model(null).build();
        Optional<CoreError> errorOptional = validator.validateModel(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Model", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateYearOfProductionShouldReturnNoErrors() {
        Integer year = LocalDate.now().getYear() - 1;
        AddVehicleRequest request = AddVehicleRequest.builder().yearOfProduction(year).build();
        Optional<CoreError> errorOptional = validator.validateYearOfProduction(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateYearOfProductionLessThanMinAllowedYearShouldReturnError() {
        int minYear = LocalDate.now().getYear() - CURRENT_YEAR_BACKWARD_REDUCER;
        Integer year = minYear - 1;
        AddVehicleRequest request = AddVehicleRequest.builder().yearOfProduction(year).build();
        Optional<CoreError> errorOptional = validator.validateYearOfProduction(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("YearOfProduction", errorOptional.get().getField());
        assertEquals("cannot be lower than " + minYear, errorOptional.get().getMessage());
    }

    @Test
    void testValidateYearOfProductionNegativeShouldReturnError() {
        Integer year = -(LocalDate.now().getYear());
        AddVehicleRequest request = AddVehicleRequest.builder().yearOfProduction(year).build();
        Optional<CoreError> errorOptional = validator.validateYearOfProduction(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("YearOfProduction", errorOptional.get().getField());
        assertEquals("cannot be negative or zero", errorOptional.get().getMessage());
    }

    @Test
    void testValidateYearOfProductionIsZeroShouldReturnError() {
        Integer year = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().yearOfProduction(year).build();
        Optional<CoreError> errorOptional = validator.validateYearOfProduction(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("YearOfProduction", errorOptional.get().getField());
        assertEquals("cannot be negative or zero", errorOptional.get().getMessage());
    }

    @Test
    void testValidateYearOfProductionMoreThanPresentShouldReturnError() {
        Integer year = LocalDate.now().getYear() + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().yearOfProduction(year).build();
        Optional<CoreError> errorOptional = validator.validateYearOfProduction(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "YearOfProduction");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than current year");
    }

    @Test
    void testValidateYearOfProductionIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().yearOfProduction(null).build();
        Optional<CoreError> errorOptional = validator.validateYearOfProduction(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "YearOfProduction");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty");
    }

    @Test
    void testValidateColourShouldReturnNoErrors() {
        String colour = "-bLAck!*";
        AddVehicleRequest request = AddVehicleRequest.builder().colour(colour).build();
        Optional<CoreError> errorOptional = validator.validateColour(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateColourIsEmptyShouldReturnErrorV1() {
        String colour = "";
        AddVehicleRequest request = AddVehicleRequest.builder().colour(colour).build();
        Optional<CoreError> errorOptional = validator.validateColour(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Colour", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateColourIsEmptyShouldReturnErrorV2() {
        String colour = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().colour(colour).build();
        Optional<CoreError> errorOptional = validator.validateColour(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Colour", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateColourIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().colour(null).build();
        Optional<CoreError> errorOptional = validator.validateColour(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Colour", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateColourWrongInputShouldReturnError() {
        String colour = "wrong";
        AddVehicleRequest request = AddVehicleRequest.builder().colour(colour).build();
        Optional<CoreError> errorOptional = validator.validateColour(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Colour", errorOptional.get().getField());
        assertEquals("must be one of the provided options (" + Colour.getAllEnumValues() + ")", errorOptional.get().getMessage());
    }

    @Test
    void testValidateRentPriceShouldReturnNoErrors() {
        Double price = 100.0;
        AddVehicleRequest request = AddVehicleRequest.builder().rentPricePerDay(price).build();
        Optional<CoreError> errorOptional = validator.validateRentPrice(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateRentPriceNegativeShouldReturnError() {
        Double price = -100.0;
        AddVehicleRequest request = AddVehicleRequest.builder().rentPricePerDay(price).build();
        Optional<CoreError> errorOptional = validator.validateRentPrice(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Rent Price", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateRentPriceIsZeroShouldReturnError() {
        Double price = 0.0;
        AddVehicleRequest request = AddVehicleRequest.builder().rentPricePerDay(price).build();
        Optional<CoreError> errorOptional = validator.validateRentPrice(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Rent Price", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateRentPriceIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().rentPricePerDay(null).build();
        Optional<CoreError> errorOptional = validator.validateRentPrice(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Rent Price", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateEngineTypeShouldReturnNoErrors() {
        String engineType = "ga/s ";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        Optional<CoreError> errorOptional = validator.validateEngineType(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateEngineTypeIsEmptyShouldReturnErrorV1() {
        String engineType = "";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        Optional<CoreError> errorOptional = validator.validateEngineType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Engine Type", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateEngineTypeIsEmptyShouldReturnErrorV2() {
        String engineType = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        Optional<CoreError> errorOptional = validator.validateEngineType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Engine Type", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateEngineTypeIsNullShouldReturnErrorV2() {
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(null).build();
        Optional<CoreError> errorOptional = validator.validateEngineType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Engine Type", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateEngineTypeWrongInputShouldReturnError() {
        String engineType = "wrong";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        Optional<CoreError> errorOptional = validator.validateEngineType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Engine Type", errorOptional.get().getField());
        assertEquals("must be one of the provided options (" + EngineType.getAllEnumValues() + ")", errorOptional.get().getMessage());
    }

    @Test
    void testValidatePlateNumberShouldReturnNoErrors() {
        String plateNumber = "plate number";
        AddVehicleRequest request = AddVehicleRequest.builder().plateNumber(plateNumber).build();
        Optional<CoreError> errorOptional = validator.validatePlateNumber(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidatePlateNumberIsEmptyShouldReturnErrorV1() {
        String plateNumber = "";
        AddVehicleRequest request = AddVehicleRequest.builder().plateNumber(plateNumber).build();
        Optional<CoreError> errorOptional = validator.validatePlateNumber(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Plate Number", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidatePlateNumberIsEmptyShouldReturnErrorV2() {
        String plateNumber = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().plateNumber(plateNumber).build();
        Optional<CoreError> errorOptional = validator.validatePlateNumber(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Plate Number", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidatePlateNumberIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().plateNumber(null).build();
        Optional<CoreError> errorOptional = validator.validatePlateNumber(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Plate Number", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeShouldReturnNoErrors() {
        String transmissionType = " ma*nua/l";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        Optional<CoreError> errorOptional = validator.validateTransmissionType(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateTransmissionTypeIsEmptyShouldReturnErrorV1() {
        String transmissionType = "";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        Optional<CoreError> errorOptional = validator.validateTransmissionType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Transmission Type", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeIsEmptyShouldReturnErrorV2() {
        String transmissionType = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        Optional<CoreError> errorOptional = validator.validateTransmissionType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Transmission Type", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(null).build();
        Optional<CoreError> errorOptional = validator.validateTransmissionType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Transmission Type", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeWrongInputShouldReturnError() {
        String transmissionType = "wrong";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        Optional<CoreError> errorOptional = validator.validateTransmissionType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Transmission Type", errorOptional.get().getField());
        assertEquals("must be one of the provided options (" + TransmissionType.getAllEnumValues() + ")", errorOptional.get().getMessage());
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
        assertEquals("Passenger amount", errorOptional.get().getField());
        assertEquals("cannot be more than " + MAX_PASSENGER_AMOUNT, errorOptional.get().getMessage());
    }

    @Test
    void testValidateBaggageAmountShouldReturnNoErrors() {
        Integer baggageAmount = MAX_BAGGAGE_AMOUNT;
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
        Integer baggageAmount = null;
        AddVehicleRequest request = AddVehicleRequest.builder().baggageAmount(baggageAmount).build();
        Optional<CoreError> errorOptional = validator.validateBaggageAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Baggage amount", errorOptional.get().getField());
        assertEquals("cannot be empty or negative", errorOptional.get().getMessage());
    }

    @Test
    void testValidateBaggageAmountMoreThanMaxAllowedShouldReturnError() {
        Integer baggageAmount = MAX_BAGGAGE_AMOUNT + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().baggageAmount(baggageAmount).build();
        Optional<CoreError> errorOptional = validator.validateBaggageAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Baggage amount", errorOptional.get().getField());
        assertEquals("cannot be more than " + MAX_BAGGAGE_AMOUNT, errorOptional.get().getMessage());
    }

    @Test
    void testValidateDoorsAmountShouldReturnNoErrors() {
        Integer doorsAmount = MAX_DOORS_AMOUNT;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isEmpty());
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
    void testValidateDoorsAmountIsNegativeShouldReturnError() {
        Integer doorsAmount = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Doors amount", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDoorsAmountIsNullShouldReturnError() {
        Integer doorsAmount = null;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Doors amount", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDoorsAmountMoreThanMaxAllowedShouldReturnError() {
        Integer doorsAmount = MAX_DOORS_AMOUNT + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Doors amount", errorOptional.get().getField());
        assertEquals("cannot be more than " + MAX_DOORS_AMOUNT, errorOptional.get().getMessage());
    }

    @Test
    void testValidateIsAirConditioningAvailableTrueShouldReturnNoErrors() {
        String isAirConditioningAvailable = "true";
        AddVehicleRequest request = AddVehicleRequest.builder().isAirConditioningAvailable(isAirConditioningAvailable).build();
        Optional<CoreError> errorOptional = validator.validateIsAirConditionerAvailable(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateIsAirConditioningAvailableFalseShouldReturnNoErrors() {
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
    }

    @Test
    void testValidateIsAirConditioningAvailableIsNullShouldReturnErrorV2() {
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