package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.CarTrailerCreator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators.AddCarTrailerValidator.*;
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
    void testValidateReturnsListOfAllErrors() {
        AddVehicleRequest request = AddVehicleRequest.builder().build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 13);
    }

    @Test
    void testValidateEngineTypeShouldReturnNoErrors() {
        String engineType = "no/ne ";
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
        String engineType = "gas";
        AddVehicleRequest request = AddVehicleRequest.builder().engineType(engineType).build();
        Optional<CoreError> errorOptional = validator.validateEngineType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Engine Type", errorOptional.get().getField());
        assertEquals("for Car Trailer must be \"None\"", errorOptional.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeShouldReturnNoErrors() {
        String transmissionType = " n*on/e";
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
        String transmissionType = "manual";
        AddVehicleRequest request = AddVehicleRequest.builder().transmissionType(transmissionType).build();
        Optional<CoreError> errorOptional = validator.validateTransmissionType(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Transmission Type", errorOptional.get().getField());
        assertEquals("for Car Trailer must be \"None\"", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckWidthInCmShouldReturnNoErrors() {
        Integer deckWidthInCm = MAX_DECK_WIDTH_IN_CM;
        AddVehicleRequest request = AddVehicleRequest.builder().deckWidthInCm(deckWidthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckWidthInCm(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateDeckWidthInCmIsZeroShouldReturnError() {
        Integer deckWidthInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().deckWidthInCm(deckWidthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckWidthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Deck Width in cm", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckWidthInCmNegativeShouldReturnError() {
        Integer deckWidthInCm = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().deckWidthInCm(deckWidthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckWidthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Deck Width in cm", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckWidthInCmIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().deckWidthInCm(null).build();
        Optional<CoreError> errorOptional = validator.validateDeckWidthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Deck Width in cm", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckWidthInCmMoreThanMaxAllowedShouldReturnError() {
        Integer deckWidthInCm = MAX_DECK_WIDTH_IN_CM + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().deckWidthInCm(deckWidthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckWidthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Deck Width in cm", errorOptional.get().getField());
        assertEquals("cannot be more than " + MAX_DECK_WIDTH_IN_CM, errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckLengthInCmShouldReturnNoErrors() {
        Integer deckLengthInCm = MAX_DECK_LENGTH_IN_CM;
        AddVehicleRequest request = AddVehicleRequest.builder().deckLengthInCm(deckLengthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckLengthInCm(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateDeckLengthInCmIsZeroShouldReturnError() {
        Integer deckLengthInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().deckLengthInCm(deckLengthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckLengthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Deck Length in cm", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckLengthInCmNegativeShouldReturnError() {
        Integer deckLengthInCm = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().deckLengthInCm(deckLengthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckLengthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Deck Length in cm", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckLengthInCmIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().deckLengthInCm(null).build();
        Optional<CoreError> errorOptional = validator.validateDeckLengthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Deck Length in cm", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckLengthInCmMoreThanMaxAllowedShouldReturnError() {
        Integer deckLengthInCm = MAX_DECK_LENGTH_IN_CM + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().deckLengthInCm(deckLengthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckLengthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Deck Length in cm", errorOptional.get().getField());
        assertEquals("cannot be more than " + MAX_DECK_LENGTH_IN_CM, errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckHeightInCmShouldReturnNoErrors() {
        Integer deckHeightInCm = MAX_DECK_HEIGHT_IN_CM;
        AddVehicleRequest request = AddVehicleRequest.builder().deckHeightInCm(deckHeightInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckHeightInCm(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateDeckHeightInCmIsZeroShouldReturnError() {
        Integer deckHeightInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().deckHeightInCm(deckHeightInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckHeightInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Deck Height in cm", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckHeightInCmNegativeShouldReturnError() {
        Integer deckHeightInCm = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().deckHeightInCm(deckHeightInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckHeightInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Deck Height in cm", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckHeightInCmIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().deckHeightInCm(null).build();
        Optional<CoreError> errorOptional = validator.validateDeckHeightInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Deck Height in cm", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateDeckHeightInCmMoreThanMaxAllowedShouldReturnError() {
        Integer deckHeightInCm = MAX_DECK_HEIGHT_IN_CM + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().deckHeightInCm(deckHeightInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckHeightInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Deck Height in cm");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + MAX_DECK_HEIGHT_IN_CM);
    }

    @Test
    void testValidateEmptyWeightInKgShouldReturnNoErrors() {
        Integer emptyWeightInKg = MAX_EMPTY_WEIGHT_IN_KG;
        AddVehicleRequest request = AddVehicleRequest.builder().emptyWeightInKg(emptyWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateEmptyWeightInKg(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateEmptyWeightInKgIsZeroShouldReturnError() {
        Integer emptyWeightInKg = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().emptyWeightInKg(emptyWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateEmptyWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Empty Weight in KG", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgNegativeShouldReturnError() {
        Integer emptyWeightInKg = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().emptyWeightInKg(emptyWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateEmptyWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Empty Weight in KG", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgIsNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().emptyWeightInKg(null).build();
        Optional<CoreError> errorOptional = validator.validateEmptyWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Empty Weight in KG", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgMoreThanMaxAllowedShouldReturnError() {
        Integer emptyWeightInKg = MAX_EMPTY_WEIGHT_IN_KG + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().emptyWeightInKg(emptyWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateEmptyWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Empty Weight in KG", errorOptional.get().getField());
        assertEquals("cannot be more than " + MAX_EMPTY_WEIGHT_IN_KG, errorOptional.get().getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgShouldReturnNoErrors() {
        Integer maxLoadWeightInKg = MAX_LOAD_WEIGHT_IN_KG;
        AddVehicleRequest request = AddVehicleRequest.builder().maxLoadWeightInKg(maxLoadWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateMaxLoadWeightInKg(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateMaxLoadWeightInKgIsZeroShouldReturnError() {
        Integer maxLoadWeightInKg = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().maxLoadWeightInKg(maxLoadWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateMaxLoadWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Max Load Weight in KG", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgNegativeShouldReturnError() {
        Integer maxLoadWeightInKg = -1;
        AddVehicleRequest request = AddVehicleRequest.builder().maxLoadWeightInKg(maxLoadWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateMaxLoadWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Max Load Weight in KG", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgNullShouldReturnError() {
        AddVehicleRequest request = AddVehicleRequest.builder().maxLoadWeightInKg(null).build();
        Optional<CoreError> errorOptional = validator.validateMaxLoadWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Max Load Weight in KG", errorOptional.get().getField());
        assertEquals("cannot be empty, negative or 0", errorOptional.get().getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgMoreThanMaxAllowedShouldReturnError() {
        Integer maxLoadWeightInKg = MAX_LOAD_WEIGHT_IN_KG + 1;
        AddVehicleRequest request = AddVehicleRequest.builder().maxLoadWeightInKg(maxLoadWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateMaxLoadWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Max Load Weight in KG", errorOptional.get().getField());
        assertEquals("cannot be more than " + MAX_LOAD_WEIGHT_IN_KG, errorOptional.get().getMessage());
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
        Optional<CoreError> error = validator.validateVehicleIsNotDuplicate(request2);
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
        Optional<CoreError> error = validator.validateVehicleIsNotDuplicate(request2);
        assertTrue(error.isPresent());
        assertEquals("Vehicle", error.get().getField());
        assertEquals("is already in the database", error.get().getMessage());
    }
}