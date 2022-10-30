package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lv.javaguru.java2.rentapp.domain.CarTrailer.*;
import static org.junit.jupiter.api.Assertions.*;

class SearchCarTrailerValidatorTest {

    SearchCarTrailerValidator searchCarTrailerValidator;

    SearchVehicleRequest searchVehicleRequest;

    @BeforeEach
    void setUp() {
        searchCarTrailerValidator = new SearchCarTrailerValidator();
    }

    @Test
    void testValidateReturnListWithErrorWhenRequestIsNull() {
        searchVehicleRequest = SearchVehicleRequest.builder().build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertEquals(1, errors.size());
        assertEquals("Vehicle Type", errors.get(0).getField());
        assertEquals("can`t be null (should be provided)", errors.get(0).getMessage());
    }

    @Test
    void testValidateReturnListWithAllErrorsWhenRequestIsNotValid() {
        searchVehicleRequest = SearchVehicleRequest.builder()
                .deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM + 1)
                .deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM + 1)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM + 1)
                .emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG + 1)
                .maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG + 1)
                .build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertEquals(6, errors.size());
    }

    @Test
    void testValidateReturnListWithNoErrorsWhenRequestIsValid() {
        searchVehicleRequest = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.CAR_TRAILER)
                .deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM)
                .deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM)
                .deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM)
                .emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG)
                .maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG)
                .build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDeckWidthInCmIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDeckWidthInCmNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckWidthInCm(null).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDeckWidthInCmIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckWidthInCm(-1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_DECK_WIDTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmIsZeroReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckWidthInCm(0).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_DECK_WIDTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmIsLessThanMinAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckWidthInCm(TRAIL_MIN_DECK_WIDTH_IN_CM - 1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_DECK_WIDTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckWidthInCmIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckWidthInCm(TRAIL_MAX_DECK_WIDTH_IN_CM + 1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Width in cm", errors.get(0).getField());
        assertEquals("can`t be more than " + TRAIL_MAX_DECK_WIDTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDeckLengthInCmNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckLengthInCm(null).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDeckLengthInCmIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckLengthInCm(-1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_DECK_LENGTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmIsZeroReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckLengthInCm(0).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_DECK_LENGTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmIsLessThanMinAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckLengthInCm(TRAIL_MIN_DECK_LENGTH_IN_CM - 1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_DECK_LENGTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckLengthInCmIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckLengthInCm(TRAIL_MAX_DECK_LENGTH_IN_CM + 1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Length in cm", errors.get(0).getField());
        assertEquals("can`t be more than " + TRAIL_MAX_DECK_LENGTH_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDeckHeightInCmNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckHeightInCm(null).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDeckHeightInCmIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckHeightInCm(-1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Height in cm", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_DECK_HEIGHT_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmIsZeroReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckHeightInCm(0).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Height in cm", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_DECK_HEIGHT_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmIsLessThanMinAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckHeightInCm(TRAIL_MIN_DECK_HEIGHT_IN_CM - 1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Height in cm", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_DECK_HEIGHT_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateDeckHeightInCmIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).deckHeightInCm(TRAIL_MAX_DECK_HEIGHT_IN_CM + 1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Deck Height in cm", errors.get(0).getField());
        assertEquals("can`t be more than " + TRAIL_MAX_DECK_HEIGHT_IN_CM, errors.get(0).getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateEmptyWeightInKgNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).emptyWeightInKg(null).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateEmptyWeightInKgIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).emptyWeightInKg(-1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_EMPTY_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgIsZeroReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).emptyWeightInKg(0).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_EMPTY_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgIsLessThanMinAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).emptyWeightInKg(TRAIL_MIN_EMPTY_WEIGHT_IN_KG - 1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_EMPTY_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateEmptyWeightInKgIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).emptyWeightInKg(TRAIL_MAX_EMPTY_WEIGHT_IN_KG + 1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Empty Weight in KG", errors.get(0).getField());
        assertEquals("can`t be more than " + TRAIL_MAX_EMPTY_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateMaxLoadWeightInKgNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).maxLoadWeightInKg(null).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateMaxLoadWeightInKgIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).maxLoadWeightInKg(-1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_LOAD_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgIsZeroReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).maxLoadWeightInKg(0).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_LOAD_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgIsLessThanMinAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).maxLoadWeightInKg(TRAIL_MIN_LOAD_WEIGHT_IN_KG - 1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + TRAIL_MIN_LOAD_WEIGHT_IN_KG, errors.get(0).getMessage());
    }

    @Test
    void testValidateMaxLoadWeightInKgIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER).maxLoadWeightInKg(TRAIL_MAX_LOAD_WEIGHT_IN_KG + 1).build();
        List<CoreError> errors = searchCarTrailerValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Max Load Weight in KG", errors.get(0).getField());
        assertEquals("can`t be more than " + TRAIL_MAX_LOAD_WEIGHT_IN_KG, errors.get(0).getMessage());
    }
}