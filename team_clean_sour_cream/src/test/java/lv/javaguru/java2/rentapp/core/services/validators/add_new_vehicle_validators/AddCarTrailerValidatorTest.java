package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddCarTrailerValidatorTest {

    AddCarTrailerValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AddCarTrailerValidator();
    }

    @Test
    void testValidateDeckWidthInCmShouldReturnNoErrors() {
        Integer deckWidthInCm = 500;
        AddVehicleRequest request = AddVehicleRequest.builder().deckWidthInCm(deckWidthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckWidthInCm(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateDeckWidthInCmShouldReturnErrorV1() {
        Integer deckWidthInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().deckWidthInCm(deckWidthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckWidthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Deck Width in cm");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty, negative or 0");
    }

    @Test
    void testValidateDeckWidthInCmShouldReturnErrorV2() {
        Integer deckWidthInCm = 5000;
        AddVehicleRequest request = AddVehicleRequest.builder().deckWidthInCm(deckWidthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckWidthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Deck Width in cm");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + AddCarTrailerValidator.MAX_DECK_WIDTH_IN_CM);
    }


    @Test
    void testValidateDeckLengthInCmShouldReturnNoErrors() {
        Integer deckLengthInCm = 500;
        AddVehicleRequest request = AddVehicleRequest.builder().deckLengthInCm(deckLengthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckLengthInCm(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateDeckLengthInCmShouldReturnErrorV1() {
        Integer deckLengthInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().deckLengthInCm(deckLengthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckLengthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Deck Length in cm");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty, negative or 0");
    }

    @Test
    void testValidateDeckLengthInCmShouldReturnErrorV2() {
        Integer deckLengthInCm = 15000;
        AddVehicleRequest request = AddVehicleRequest.builder().deckLengthInCm(deckLengthInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckLengthInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Deck Length in cm");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + AddCarTrailerValidator.MAX_DECK_LENGTH_IN_CM);
    }

    @Test
    void testValidateDeckHeightInCmShouldReturnNoErrors() {
        Integer deckHeightInCm = 500;
        AddVehicleRequest request = AddVehicleRequest.builder().deckHeightInCm(deckHeightInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckHeightInCm(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateDeckHeightInCmShouldReturnErrorV1() {
        Integer deckHeightInCm = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().deckHeightInCm(deckHeightInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckHeightInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Deck Height in cm");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty, negative or 0");
    }

    @Test
    void testValidateDeckHeightInCmShouldReturnErrorV2() {
        Integer deckHeightInCm = 600;
        AddVehicleRequest request = AddVehicleRequest.builder().deckHeightInCm(deckHeightInCm).build();
        Optional<CoreError> errorOptional = validator.validateDeckHeightInCm(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Deck Height in cm");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + AddCarTrailerValidator.MAX_DECK_HEIGHT_IN_CM);
    }

    @Test
    void testValidateEmptyWeightInKgShouldReturnNoErrors() {
        Integer emptyWeightInKg = 600;
        AddVehicleRequest request = AddVehicleRequest.builder().emptyWeightInKg(emptyWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateEmptyWeightInKg(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateEmptyWeightInKgShouldReturnErrorV1() {
        Integer emptyWeightInKg = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().emptyWeightInKg(emptyWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateEmptyWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Empty Weight in KG");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty, negative or 0");
    }

    @Test
    void testValidateEmptyWeightInKgShouldReturnErrorV2() {
        Integer emptyWeightInKg = 6000;
        AddVehicleRequest request = AddVehicleRequest.builder().emptyWeightInKg(emptyWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateEmptyWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Empty Weight in KG");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + AddCarTrailerValidator.MAX_EMPTY_WEIGHT_IN_KG);
    }

    @Test
    void testValidateMaxLoadWeightInKgShouldReturnNoErrors() {
        Integer maxLoadWeightInKg = 600;
        AddVehicleRequest request = AddVehicleRequest.builder().maxLoadWeightInKg(maxLoadWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateMaxLoadWeightInKg(request);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    void testValidateMaxLoadWeightInKgShouldReturnErrorV1() {
        Integer maxLoadWeightInKg = 0;
        AddVehicleRequest request = AddVehicleRequest.builder().maxLoadWeightInKg(maxLoadWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateMaxLoadWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Max Load Weight in KG");
        assertEquals(errorOptional.get().getMessage(), "cannot be empty, negative or 0");
    }

    @Test
    void testValidateMaxLoadWeightInKgShouldReturnErrorV2() {
        Integer maxLoadWeightInKg = 11000;
        AddVehicleRequest request = AddVehicleRequest.builder().maxLoadWeightInKg(maxLoadWeightInKg).build();
        Optional<CoreError> errorOptional = validator.validateMaxLoadWeightInKg(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "Max Load Weight in KG");
        assertEquals(errorOptional.get().getMessage(), "cannot be more than " + AddCarTrailerValidator.MAX_LOAD_WEIGHT_IN_KG);
    }

    @Test
    void testValidateReturnsListOfAllErrors() {
        AddVehicleRequest request = AddVehicleRequest.builder().build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 13);
    }
}