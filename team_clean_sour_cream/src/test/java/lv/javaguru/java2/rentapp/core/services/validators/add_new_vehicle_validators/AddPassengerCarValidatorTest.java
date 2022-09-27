package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddPassengerCarValidator.MAX_BAGGAGE_AMOUNT;
import static lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddPassengerCarValidator.MAX_PASSENGER_AMOUNT;
import static lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddVehicleValidator.CURRENT_YEAR_BACKWARD_REDUCER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddPassengerCarValidatorTest {

    AddPassengerCarValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AddPassengerCarValidator();
    }

    @Test
    void testValidateReturnsListOfAllErrors() {
        AddVehicleRequest request = AddVehicleRequest.builder().build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(12, errors.size());
    }

    @Test
    void testValidateBrandShouldReturnNoErrors() {
        String brand = "brand";
        AddVehicleRequest request = AddVehicleRequest.builder().brand(brand).build();
        Optional<CoreError> errorOptional = validator.validateBrand(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateBrandShouldReturnErrorV1() {
        String brand = "";
        AddVehicleRequest request = AddVehicleRequest.builder().brand(brand).build();
        Optional<CoreError> errorOptional = validator.validateBrand(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Brand", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateBrandShouldReturnErrorV2() {
        String brand = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().brand(brand).build();
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
    void testValidateModelShouldReturnErrorV1() {
        String model = "";
        AddVehicleRequest request = AddVehicleRequest.builder().model(model).build();
        Optional<CoreError> errorOptional = validator.validateModel(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Model", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateModelShouldReturnErrorV2() {
        String model = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().model(model).build();
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
    void testValidateNegativeYearOfProductionShouldReturnError() {
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
    void testValidateColourShouldReturnNoErrors() {
        String colour = "-bLAck!*";
        AddVehicleRequest request = AddVehicleRequest.builder().colour(colour).build();
        Optional<CoreError> errorOptional = validator.validateColour(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateColourShouldReturnErrorV1() {
        String colour = "";
        AddVehicleRequest request = AddVehicleRequest.builder().colour(colour).build();
        Optional<CoreError> errorOptional = validator.validateColour(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Colour", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateColourShouldReturnErrorV2() {
        String colour = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().colour(colour).build();
        Optional<CoreError> errorOptional = validator.validateColour(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Colour", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateColourShouldReturnErrorV3() {
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
    void testValidateEngineTypeShouldReturnNoErrors() {
        String engineType = "ga/s ";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        Optional<CoreError> errorOptional = validator.validateEngineType(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateEngineTypeShouldReturnErrorV1() {
        String engineType = "";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        Optional<CoreError> errorOptional = validator.validateEngineType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Engine Type", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateEngineTypeShouldReturnErrorV2() {
        String engineType = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        Optional<CoreError> errorOptional = validator.validateEngineType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Engine Type", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateEngineTypeShouldReturnErrorV3() {
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
    void testValidatePlateNumberShouldReturnErrorV1() {
        String plateNumber = "";
        AddVehicleRequest request = AddVehicleRequest.builder().plateNumber(plateNumber).build();
        Optional<CoreError> errorOptional = validator.validatePlateNumber(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Plate Number", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }


    @Test
    void testValidatePlateNumberShouldReturnErrorV2() {
        String plateNumber = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().plateNumber(plateNumber).build();
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
    void testValidateTransmissionTypeShouldReturnErrorV1() {
        String transmissionType = "";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        Optional<CoreError> errorOptional = validator.validateTransmissionType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Transmission Type", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeShouldReturnErrorV2() {
        String transmissionType = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        Optional<CoreError> errorOptional = validator.validateTransmissionType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Transmission Type", errorOptional.get().getField());
        assertEquals("cannot be empty", errorOptional.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeShouldReturnErrorV3() {
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
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + MAX_BAGGAGE_AMOUNT);
    }

    @Test
    void testValidateDoorsAmountShouldReturnErrorV2() {
        Integer doorsAmount = 11;
        AddVehicleRequest request = AddVehicleRequest.builder().doorsAmount(doorsAmount).build();
        Optional<CoreError> errorOptional = validator.validateDoorsAmount(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Doors amount");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + AddPassengerCarValidator.MAX_DOORS_AMOUNT);
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