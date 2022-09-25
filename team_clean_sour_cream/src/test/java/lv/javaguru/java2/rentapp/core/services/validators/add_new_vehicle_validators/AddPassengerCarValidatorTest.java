package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(errors.size(), 12);
    }

    @Test
    void testValidateBrandShouldReturnNoErrors() {
        String brand = "brand";
        AddVehicleRequest request = AddVehicleRequest.builder().brand(brand).build();
        List<CoreError> errors = new ArrayList<>();
        validator.validateBrand(request).ifPresent(errors::add);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateBrandShouldReturnError() {
        String brand = "";
        AddVehicleRequest request = AddVehicleRequest.builder().brand(brand).build();
        List<CoreError> errors = new ArrayList<>();
        validator.validateBrand(request).ifPresent(errors::add);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Brand");
        assertEquals(errors.get(0).getMessage(), "cannot be empty");
    }

    @Test
    void testValidateModelShouldReturnNoErrors() {
        String model = "model";
        AddVehicleRequest request = AddVehicleRequest.builder().model(model).build();
        Optional<CoreError> errorOptional = validator.validateModel(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateModelShouldReturnError() {
        String model = "";
        AddVehicleRequest request = AddVehicleRequest.builder().model(model).build();
        Optional<CoreError> errorOptional = validator.validateModel(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Model");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty");
    }

    @Test
    void testValidateYearOfProductionShouldReturnNoErrors() {
        Integer year = 2000;
        AddVehicleRequest request = AddVehicleRequest.builder().yearOfProduction(year).build();
        Optional<CoreError> errorOptional = validator.validateYearOfProduction(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateNegativeYearOfProductionShouldReturnError() {
        Integer year = -2000;
        int minYear = LocalDate.now().getYear() - 100;
        AddVehicleRequest request = AddVehicleRequest.builder().yearOfProduction(year).build();
        Optional<CoreError> errorOptional = validator.validateYearOfProduction(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "YearOfProduction");
        assertEquals(errorOptional.get().getMessage(), "cannot be lower than " + minYear);
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
        String colour = "red";
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
        assertEquals(errorOptional.get().getField(), "Colour");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty");
    }

    @Test
    void testValidateColourShouldReturnErrorV2() {
        String colour = "wrong";
        AddVehicleRequest request = AddVehicleRequest.builder().colour(colour).build();
        Optional<CoreError> errorOptional = validator.validateColour(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Colour");
        assertEquals(errorOptional.get().getMessage(), "must be one of the provided options (Red, black, blue, green, orange, white or yellow");
    }

    @Test
    void testValidateRentPriceShouldReturnNoErrors() {
        Double price = 100.0;
        AddVehicleRequest request = AddVehicleRequest.builder().rentPricePerDay(price).build();
        Optional<CoreError> errorOptional = validator.validateRentPrice(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateRentPriceShouldReturnError() {
        Double price = -100.0;
        AddVehicleRequest request = AddVehicleRequest.builder().rentPricePerDay(price).build();
        Optional<CoreError> errorOptional = validator.validateRentPrice(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Rent Price");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty, negative or 0");
    }

    @Test
    void testValidateEngineTypeShouldReturnNoErrors() {
        String engineType = "gas";
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
        assertEquals(errorOptional.get().getField(), "Engine Type");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty");
    }

    @Test
    void testValidateEngineTypeShouldReturnErrorV2() {
        String engineType = "wrong";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        Optional<CoreError> errorOptional = validator.validateEngineType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Engine Type");
        assertEquals(errorOptional.get().getMessage(), "must be one of the provided options (Diesel, Electric, Gas, Petrol, Hybrid, None");
    }

    @Test
    void testValidatePlateNumberShouldReturnNoErrors() {
        String plateNumber = "plate number";
        AddVehicleRequest request = AddVehicleRequest.builder().plateNumber(plateNumber).build();
        Optional<CoreError> errorOptional = validator.validatePlateNumber(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidatePlateNumberShouldReturnError() {
        String plateNumber = "";
        AddVehicleRequest request = AddVehicleRequest.builder().plateNumber(plateNumber).build();
        Optional<CoreError> errorOptional = validator.validatePlateNumber(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Plate Number");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty");
    }

    @Test
    void testValidateTransmissionTypeShouldReturnNoErrors() {
        String transmissionType = "manual";
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
        assertEquals(errorOptional.get().getField(), "Transmission Type");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty");
    }

    @Test
    void testValidateTransmissionTypeShouldReturnErrorV2() {
        String transmissionType = "wrong";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        Optional<CoreError> errorOptional = validator.validateTransmissionType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Transmission Type");
        assertEquals(errorOptional.get().getMessage(), "must be one of the provided options (Automatic, Manual or None");
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
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + AddPassengerCarValidator.MAX_PASSENGER_AMOUNT);
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
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + AddPassengerCarValidator.MAX_BAGGAGE_AMOUNT);
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