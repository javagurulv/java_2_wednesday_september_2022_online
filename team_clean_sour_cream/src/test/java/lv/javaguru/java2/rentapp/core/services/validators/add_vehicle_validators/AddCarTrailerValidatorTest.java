package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.CarTrailerCreator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static lv.javaguru.java2.rentapp.domain.CarTrailer.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddCarTrailerValidatorTest {

    AddCarTrailerValidator validator;
    private Database database;

    @BeforeEach
    void setUp() {
        database = new InMemoryDatabaseImpl();
        validator = new AddCarTrailerValidator(database);
    }

    @Test
    void testValidateEngineTypeShouldReturnNoErrors() {
        String engineType = "no/ne ";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateEngineTypeIsEmptyShouldReturnErrorV1() {
        String engineType = "";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Engine Type", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateEngineTypeIsEmptyShouldReturnErrorV2() {
        String engineType = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Engine Type", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateEngineTypeIsNullShouldReturnErrorV2() {
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(null).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Engine Type", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateEngineTypeWrongInputShouldReturnError() {
        String engineType = "gas";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Engine Type", errors.get(0).getField());
        assertEquals("for Car Trailer must be \"None\"", errors.get(0).getMessage());
    }

    @Test
    void testValidateTransmissionTypeShouldReturnNoErrors() {
        String transmissionType = " n*on/e";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        List<CoreError> errorOptional = validator.validate(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateTransmissionTypeIsEmptyShouldReturnErrorV1() {
        String transmissionType = "";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Transmission Type", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateTransmissionTypeIsEmptyShouldReturnErrorV2() {
        String transmissionType = " ";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Transmission Type", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateTransmissionTypeIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(null).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Transmission Type", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }


    @Test
    void testValidateTransmissionTypeWrongInputShouldReturnError() {
        String transmissionType = "manual";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Transmission Type", errors.get(0).getField());
        assertEquals("for Car Trailer must be \"None\"", errors.get(0).getMessage());
    }

    @Test
    void testValidateReturnsEmptyList() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    void testValidateVehicleIsNotDuplicateShouldReturnNoErrors() {
        AddVehicleRequest request1 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(10).deckLengthInCm(10).deckHeightInCm(10).emptyWeightInKg(10).maxLoadWeightInKg(10).build();
        Vehicle carTrailer1 = new CarTrailerCreator().createVehicle(request1);
        database.addNewVehicle(carTrailer1);
        AddVehicleRequest request2 = AddVehicleRequest.builder().brand("brand2").model("model2").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(10).deckLengthInCm(10).deckHeightInCm(10).emptyWeightInKg(10).maxLoadWeightInKg(10).build();
        List<CoreError> error = validator.validate(request2);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateVehicleIsNotDuplicateShouldReturnError() {
        AddVehicleRequest request1 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(10).deckLengthInCm(10).deckHeightInCm(10).emptyWeightInKg(10).maxLoadWeightInKg(10).build();
        Vehicle carTrailer1 = new CarTrailerCreator().createVehicle(request1);
        database.addNewVehicle(carTrailer1);
        AddVehicleRequest request2 = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(2000).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(10).deckLengthInCm(10).deckHeightInCm(10).emptyWeightInKg(10).maxLoadWeightInKg(10).build();
        List<CoreError> error = validator.validate(request2);
        assertEquals(1, error.size());
        assertEquals("Vehicle", error.get(0).getField());
        assertEquals("is already in the database", error.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmShouldReturnNoErrors() {
        Integer deckWidthInCm = TRAIL_MAX_DECK_WIDTH_IN_CM;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(deckWidthInCm).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errorOptional = validator.validate(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateDeckWidthInCmIsZeroShouldReturnError() {
        Integer deckWidthInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(deckWidthInCm).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmNegativeShouldReturnError() {
        Integer deckWidthInCm = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(deckWidthInCm).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(null).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmMoreThanMaxAllowedShouldReturnError() {
        Integer deckWidthInCm = TRAIL_MAX_DECK_WIDTH_IN_CM + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(deckWidthInCm).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("cannot be more than " + TRAIL_MAX_DECK_WIDTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmShouldReturnNoErrors() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDeckLengthInCmIsZeroShouldReturnError() {
        Integer deckLengthInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(deckLengthInCm)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmNegativeShouldReturnError() {
        Integer deckLengthInCm = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(deckLengthInCm)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(null)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmMoreThanMaxAllowedShouldReturnError() {
        Integer deckLengthInCm = TRAIL_MAX_DECK_LENGTH_IN_CM + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(deckLengthInCm)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("cannot be more than " + TRAIL_MAX_DECK_LENGTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmShouldReturnNoErrors() {
        Integer deckHeightInCm = TRAIL_MAX_DECK_HEIGHT_IN_CM;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(deckHeightInCm).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDeckHeightInCmIsZeroShouldReturnError() {
        Integer deckHeightInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(deckHeightInCm).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Height in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmNegativeShouldReturnError() {
        Integer deckHeightInCm = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(deckHeightInCm).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Height in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(null).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Deck Height in cm", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmMoreThanMaxAllowedShouldReturnError() {
        Integer deckHeightInCm = TRAIL_MAX_DECK_HEIGHT_IN_CM + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(deckHeightInCm).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Deck Height in cm");
        assertEquals(errors.get(0).getMessage(), "cannot be more than " + TRAIL_MAX_DECK_HEIGHT_IN_CM);
    }

    @Test
    void testValidateEmptyWeightInKgShouldReturnNoErrors() {
        Integer emptyWeightInKg = TRAIL_MAX_EMPTY_WEIGHT_IN_KG;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(emptyWeightInKg).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateEmptyWeightInKgIsZeroShouldReturnError() {
        Integer emptyWeightInKg = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(emptyWeightInKg).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgNegativeShouldReturnError() {
        Integer emptyWeightInKg = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(emptyWeightInKg).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(null).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgMoreThanMaxAllowedShouldReturnError() {
        Integer emptyWeightInKg = TRAIL_MAX_EMPTY_WEIGHT_IN_KG + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(emptyWeightInKg).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("cannot be more than " + TRAIL_MAX_EMPTY_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgShouldReturnNoErrors() {
        Integer maxLoadWeightInKg = TRAIL_MAX_LOAD_WEIGHT_IN_KG;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(maxLoadWeightInKg).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateMaxLoadWeightInKgIsZeroShouldReturnError() {
        Integer maxLoadWeightInKg = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(maxLoadWeightInKg).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgNegativeShouldReturnError() {
        Integer maxLoadWeightInKg = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(maxLoadWeightInKg).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(null).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("cannot be empty, negative or 0", errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgMoreThanMaxAllowedShouldReturnError() {
        Integer maxLoadWeightInKg = TRAIL_MAX_LOAD_WEIGHT_IN_KG + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().brand("brand1").model("model1").isAvailableForRent(true)
                .yearOfProduction(LocalDate.now().getYear()).colour("red").rentPricePerDay(10.0).engineType("gas").plateNumber("number1")
                .transmissionType("manual").deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).maxLoadWeightInKg(maxLoadWeightInKg).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("cannot be more than " + TRAIL_MAX_LOAD_WEIGHT_IN_KG, errors.get(0).getMessage());
    }
}