package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators.AddCarTrailerValidator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddCarTrailerValidatorTest {

    AddCarTrailerValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AddCarTrailerValidator();
    }


    @Test
    void testValidateReturnsListOfAllErrors() {
        AddVehicleRequest request = AddVehicleRequest.builder().build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 13);
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
}