package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lv.javaguru.java2.rentapp.domain.MiniBus.*;
import static org.junit.jupiter.api.Assertions.*;

class SearchMiniBusValidatorTest {

    SearchMiniBusValidator searchMiniBusValidator;

    SearchVehicleRequest searchVehicleRequest;

    @BeforeEach
    void setUp() {
        searchMiniBusValidator = new SearchMiniBusValidator();
    }

    @Test
    void testValidateReturnListWithErrorWhenRequestIsNull() {
        searchVehicleRequest = SearchVehicleRequest.builder().build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertEquals(1, errors.size());
        assertEquals("Vehicle Type", errors.get(0).getField());
        assertEquals("can`t be null (should be provided)", errors.get(0).getMessage());
    }

    @Test
    void testValidateReturnListWithAllErrorsWhenRequestIsNotValid() {
        searchVehicleRequest = SearchVehicleRequest.builder()
                .transmissionType("")
                .hasConditioner("")
                .passengerAmount(BUS_MAX_PASSENGER_AMOUNT + 1)
                .doorsAmount(BUS_MAX_DOORS_AMOUNT + 1)
                .baggageAmount(BUS_MAX_BAGGAGE_AMOUNT + 1)
                .build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertEquals(6, errors.size());
    }

    @Test
    void testValidateReturnListWithNoErrorsWhenRequestIsValid() {
        searchVehicleRequest = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.MINIBUS)
                .transmissionType("manual")
                .hasConditioner("true")
                .passengerAmount(BUS_MAX_PASSENGER_AMOUNT)
                .doorsAmount(BUS_MAX_DOORS_AMOUNT)
                .baggageAmount(BUS_MAX_BAGGAGE_AMOUNT)
                .build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidatePassengerAmountIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).passengerAmount(BUS_MAX_PASSENGER_AMOUNT).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidatePassengerAmountNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).passengerAmount(null).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidatePassengerAmountIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).passengerAmount(-1).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + BUS_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountIsZeroReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).passengerAmount(0).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + BUS_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountIsLessThanMinAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).passengerAmount(BUS_MIN_PASSENGER_AMOUNT - 1).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + BUS_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).passengerAmount(BUS_MAX_PASSENGER_AMOUNT + 1).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be more than " + BUS_MAX_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateDoorsAmountIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).doorsAmount(BUS_MAX_DOORS_AMOUNT).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDoorsAmountNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).doorsAmount(null).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDoorsAmountIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).doorsAmount(-1).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + BUS_MIN_DOORS_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateDoorsAmountIsZeroReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).doorsAmount(0).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + BUS_MIN_DOORS_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateDoorsAmountIsLessThanMinAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).doorsAmount(BUS_MIN_DOORS_AMOUNT - 1).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + BUS_MIN_DOORS_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateDoorsAmountIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).doorsAmount(BUS_MAX_DOORS_AMOUNT + 1).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("can`t be more than " + BUS_MAX_DOORS_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateBaggageAmountIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).baggageAmount(BUS_MAX_BAGGAGE_AMOUNT).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateBaggageAmountNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).baggageAmount(null).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateBaggageAmountIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).baggageAmount(-1).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Baggage amount", errors.get(0).getField());
        assertEquals("can`t be negative", errors.get(0).getMessage());
    }

    @Test
    void testValidateBaggageAmountIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.MINIBUS).baggageAmount(BUS_MAX_BAGGAGE_AMOUNT + 1).build();
        List<CoreError> errors = searchMiniBusValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Baggage amount", errors.get(0).getField());
        assertEquals("can`t be more than " + BUS_MAX_BAGGAGE_AMOUNT, errors.get(0).getMessage());
    }
}