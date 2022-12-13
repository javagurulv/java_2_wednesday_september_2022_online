package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.database.VehicleDatabaseImpl;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.CarTrailerCreator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.domain.CarTrailer.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddCarTrailerValidatorTest {

    AddCarTrailerValidator validator;
    private VehicleDatabase vehicleDatabase;

    @BeforeEach
    void setUp() {
        vehicleDatabase = Mockito.mock(VehicleDatabaseImpl.class);
        validator = new AddCarTrailerValidator(vehicleDatabase);
    }

    @Test
    void testValidateEngineTypeShouldReturnNoErrors() {
        String engineType = " n*on/e";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType(engineType).plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errorOptional = validator.validate(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateEngineTypeIsEmptyShouldReturnErrorV1() {
        String engineType = "";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType(engineType).plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        Optional<CoreError> errors = validator.validateEngineType(request);
        assertTrue(errors.isPresent());
        assertEquals("Engine Type", errors.get().getField());
        assertEquals("cannot be empty", errors.get().getMessage());
    }

    @Test
    void testValidateEngineTypeIsEmptyShouldReturnErrorV2() {
        String engineType = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType(engineType).plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        Optional<CoreError> error = validator.validateEngineType(request);
        assertTrue(error.isPresent());
        assertEquals("Engine Type", error.get().getField());
        assertEquals("cannot be empty", error.get().getMessage());
    }

    @Test
    void testValidateEngineTypeIsNullShouldReturnErrorV3() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType(null).plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        Optional<CoreError> error = validator.validateEngineType(request);
        assertTrue(error.isPresent());
        assertEquals("Engine Type", error.get().getField());
        assertEquals("cannot be empty", error.get().getMessage());
    }

    @Test
    void testValidateEngineTypeWrongInputShouldReturnError() {
        String engineType = "gas";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType(engineType).plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        Optional<CoreError> errors = validator.validateEngineType(request);
        assertTrue(errors.isPresent());
        assertEquals("Engine Type", errors.get().getField());
        assertEquals("for Car Trailer must be \"" + EngineType.NONE.getNameEngineType() + "\"", errors.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeShouldReturnNoErrors() {
        String transmissionType = " n*on/e";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType(transmissionType).deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errorOptional = validator.validate(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateTransmissionTypeIsEmptyShouldReturnErrorV1() {
        String transmissionType = "";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType(transmissionType).deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        Optional<CoreError> error = validator.validateTransmissionType(request);
        assertTrue(error.isPresent());
        assertEquals("Transmission Type", error.get().getField());
        assertEquals("cannot be empty", error.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeIsEmptyShouldReturnErrorV2() {
        String transmissionType = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType(transmissionType).deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        Optional<CoreError> error = validator.validateTransmissionType(request);
        assertTrue(error.isPresent());
        assertEquals("Transmission Type", error.get().getField());
        assertEquals("cannot be empty", error.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeIsNullShouldReturnErrorV3() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType(null).deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        Optional<CoreError> error = validator.validateTransmissionType(request);
        assertTrue(error.isPresent());
        assertEquals("Transmission Type", error.get().getField());
        assertEquals("cannot be empty", error.get().getMessage());
    }


    @Test
    void testValidateTransmissionTypeWrongInputShouldReturnError() {
        String transmissionType = "manual";
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType(transmissionType).deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Transmission Type", errors.get(0).getField());
        assertEquals("for Car Trailer must be \"" + TransmissionType.NONE.getNameTransmissionType() + "\"", errors.get(0).getMessage());
    }

    @Test
    void testValidateReturnsEmptyList() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    @Disabled
    void testValidateVehicleIsNotDuplicateShouldReturnNoErrors() {
        AddVehicleRequest request1 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MIN_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MIN_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MIN_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MIN_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MIN_LOAD_WEIGHT_IN_KG).build();
        Vehicle carTrailer1 = new CarTrailerCreator().createVehicle(request1);
        when(vehicleDatabase.getAllVehicles()).thenReturn(List.of(carTrailer1 ));
        AddVehicleRequest request2 = AddVehicleRequest.builder().brand("brand2").model("model2").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MIN_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MIN_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MIN_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MIN_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MIN_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> error = validator.validate(request2);
        assertTrue(error.isEmpty());
    }

    @Test
    @Disabled
    void testValidateVehicleIsDuplicateShouldReturnError() {
        AddVehicleRequest request1 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MIN_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MIN_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MIN_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MIN_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MIN_LOAD_WEIGHT_IN_KG).build();
        Vehicle carTrailer1 = new CarTrailerCreator().createVehicle(request1);
        when(vehicleDatabase.getAllVehicles()).thenReturn(List.of(carTrailer1 ));
        AddVehicleRequest request2 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MIN_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MIN_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MIN_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MIN_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MIN_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> error = validator.validate(request2);
        assertEquals(1, error.size());
        assertEquals("Vehicle", error.get(0).getField());
        assertEquals("is already in the database", error.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmShouldReturnNoErrors() {
        Integer deckWidthInCm = TRAIL_MAX_DECK_WIDTH_IN_CM;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(deckWidthInCm).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errorOptional = validator.validate(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateDeckWidthInCmIsZeroShouldReturnError() {
        Integer deckWidthInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(deckWidthInCm).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_WIDTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmNegativeShouldReturnError() {
        Integer deckWidthInCm = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(deckWidthInCm).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_WIDTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(null).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_WIDTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmMoreThanMaxAllowedShouldReturnError() {
        Integer deckWidthInCm = TRAIL_MAX_DECK_WIDTH_IN_CM + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(deckWidthInCm).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("cannot be more than " + TRAIL_MAX_DECK_WIDTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmLessThanMinAllowedShouldReturnError() {
        Integer deckWidthInCm = TRAIL_MIN_DECK_WIDTH_IN_CM - 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(deckWidthInCm).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_WIDTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmShouldReturnNoErrors() {
        Integer deckLengthInCm = TRAIL_MAX_DECK_LENGTH_IN_CM;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(deckLengthInCm)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDeckLengthInCmIsZeroShouldReturnError() {
        Integer deckLengthInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(deckLengthInCm)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_LENGTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmNegativeShouldReturnError() {
        Integer deckLengthInCm = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(deckLengthInCm)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_LENGTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(null)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_LENGTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmMoreThanMaxAllowedShouldReturnError() {
        Integer deckLengthInCm = TRAIL_MAX_DECK_LENGTH_IN_CM + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(deckLengthInCm)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("cannot be more than " + TRAIL_MAX_DECK_LENGTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmLessThanMinAllowedShouldReturnError() {
        Integer deckLengthInCm = TRAIL_MIN_DECK_LENGTH_IN_CM - 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(deckLengthInCm)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_LENGTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmShouldReturnNoErrors() {
        Integer deckHeightInCm = TRAIL_MAX_DECK_HEIGHT_IN_CM;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(deckHeightInCm).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDeckHeightInCmIsZeroShouldReturnError() {
        Integer deckHeightInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(deckHeightInCm).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Height in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_HEIGHT_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmNegativeShouldReturnError() {
        Integer deckHeightInCm = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(deckHeightInCm).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Height in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_HEIGHT_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(null).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Height in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_HEIGHT_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmMoreThanMaxAllowedShouldReturnError() {
        Integer deckHeightInCm = TRAIL_MAX_DECK_HEIGHT_IN_CM + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(deckHeightInCm).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Deck Height in cm");
        assertEquals(errors.get(0).getMessage(), "cannot be more than " + TRAIL_MAX_DECK_HEIGHT_IN_CM);
    }

    @Test
    void testValidateDeckHeightInCmLessThanMinAllowedShouldReturnError() {
        Integer deckHeightInCm = TRAIL_MIN_DECK_HEIGHT_IN_CM - 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(deckHeightInCm).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Deck Height in cm");
        assertEquals(errors.get(0).getMessage(), "cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_HEIGHT_IN_CM);
    }

    @Test
    void testValidateEmptyWeightInKgShouldReturnNoErrors() {
        Integer emptyWeightInKg = TRAIL_MAX_EMPTY_WEIGHT_IN_KG;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(emptyWeightInKg).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateEmptyWeightInKgIsZeroShouldReturnError() {
        Integer emptyWeightInKg = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(emptyWeightInKg).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_EMPTY_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgNegativeShouldReturnError() {
        Integer emptyWeightInKg = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(emptyWeightInKg).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_EMPTY_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(null).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_EMPTY_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgMoreThanMaxAllowedShouldReturnError() {
        Integer emptyWeightInKg = TRAIL_MAX_EMPTY_WEIGHT_IN_KG + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(emptyWeightInKg).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("cannot be more than " + TRAIL_MAX_EMPTY_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgLessThanMinAllowedShouldReturnError() {
        Integer emptyWeightInKg = TRAIL_MIN_EMPTY_WEIGHT_IN_KG - 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(emptyWeightInKg).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_EMPTY_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgShouldReturnNoErrors() {
        Integer maxLoadWeightInKg = TRAIL_MAX_LOAD_WEIGHT_IN_KG;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(maxLoadWeightInKg).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateMaxLoadWeightInKgIsZeroShouldReturnError() {
        Integer maxLoadWeightInKg = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(maxLoadWeightInKg).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_LOAD_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgNegativeShouldReturnError() {
        Integer maxLoadWeightInKg = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(maxLoadWeightInKg).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_LOAD_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(null).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_LOAD_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgMoreThanMaxAllowedShouldReturnError() {
        Integer maxLoadWeightInKg = TRAIL_MAX_LOAD_WEIGHT_IN_KG + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(maxLoadWeightInKg).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("cannot be more than " + TRAIL_MAX_LOAD_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgLessThanMinAllowedShouldReturnError() {
        Integer maxLoadWeightInKg = TRAIL_MIN_LOAD_WEIGHT_IN_KG - 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("none").plateNumber("number1")
                .transmissionType("none").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(maxLoadWeightInKg).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative, zero or less than " + TRAIL_MIN_LOAD_WEIGHT_IN_KG, errors.get(0).getMessage());
    }
}