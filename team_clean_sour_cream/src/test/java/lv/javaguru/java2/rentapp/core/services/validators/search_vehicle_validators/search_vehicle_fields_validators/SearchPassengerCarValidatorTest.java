package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.domain.PassengerCar.*;
import static org.junit.jupiter.api.Assertions.*;

class SearchPassengerCarValidatorTest {

    SearchPassengerCarValidator searchPassengerCarValidator;

    SearchVehicleRequest searchVehicleRequest;

    @BeforeEach
    void setUp() {
        searchPassengerCarValidator = new SearchPassengerCarValidator();
    }

    @Test
    void testValidateReturnListWithErrorWhenRequestIsNull() {
        searchVehicleRequest = SearchVehicleRequest.builder().build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertEquals(1, errors.size());
        assertEquals("Vehicle Type", errors.get(0).getField());
        assertEquals("can`t be null (should be provided)", errors.get(0).getMessage());
    }

    @Test
    void testValidateReturnListWithAllErrorsWhenRequestIsNotValid() {
        searchVehicleRequest = SearchVehicleRequest.builder()
                .transmissionType("")
                .hasConditioner("")
                .passengerAmount(CAR_MAX_PASSENGER_AMOUNT + 1)
                .doorsAmount(CAR_MAX_DOORS_AMOUNT + 1)
                .baggageAmount(CAR_MAX_BAGGAGE_AMOUNT + 1)
                .build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertEquals(6, errors.size());
    }

    @Test
    void testValidateReturnListWithNoErrorsWhenRequestIsValid() {
        searchVehicleRequest = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.PASSENGER_CAR)
                .transmissionType("manual")
                .hasConditioner("true")
                .passengerAmount(CAR_MAX_PASSENGER_AMOUNT)
                .doorsAmount(CAR_MIN_DOORS_AMOUNT)
                .baggageAmount(CAR_MAX_BAGGAGE_AMOUNT)
                .build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateVehicleTypeIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).build();
        Optional<CoreError> error = searchPassengerCarValidator.validateVehicleType(searchVehicleRequest);
        assertFalse(error.isPresent());
    }

    @Test
    void testValidateVehicleTypeIsNullReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(null).build();
        Optional<CoreError> error = searchPassengerCarValidator.validateVehicleType(searchVehicleRequest);
        assertTrue(error.isPresent());
        assertEquals("Vehicle Type", error.get().getField());
        assertEquals("can`t be null (should be provided)", error.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().transmissionType("Ma*n.. ual").build();
        Optional<CoreError> error = searchPassengerCarValidator.validateTransmissionType(searchVehicleRequest);
        assertFalse(error.isPresent());
    }

    @Test
    void testValidateTransmissionTypeIsNullReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().transmissionType(null).build();
        Optional<CoreError> error = searchPassengerCarValidator.validateTransmissionType(searchVehicleRequest);
        assertFalse(error.isPresent());
        assertEquals(Optional.empty(), error);
    }

    @Test
    void testValidateTransmissionTypeIsEmptyReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().transmissionType("").build();
        Optional<CoreError> error = searchPassengerCarValidator.validateTransmissionType(searchVehicleRequest);
        assertTrue(error.isPresent());
        assertEquals("Transmission Type", error.get().getField());
        assertEquals("can`t be empty or blank", error.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeIsBlankReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().transmissionType(" ").build();
        Optional<CoreError> error = searchPassengerCarValidator.validateTransmissionType(searchVehicleRequest);
        assertTrue(error.isPresent());
        assertEquals("Transmission Type", error.get().getField());
        assertEquals("can`t be empty or blank", error.get().getMessage());
    }

    @Test
    void testValidateTransmissionTypeIsNotValidReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().transmissionType("not valid").build();
        Optional<CoreError> error = searchPassengerCarValidator.validateTransmissionType(searchVehicleRequest);
        assertTrue(error.isPresent());
        assertEquals("Transmission Type", error.get().getField());
        assertEquals("must be one of the provided options (" + TransmissionType.getAllEnumValues() + ")", error.get().getMessage());
    }

    @Test
    void testValidateIsAirConditionerAvailableValidReturnNoError1() {
        searchVehicleRequest = SearchVehicleRequest.builder().hasConditioner(" Tr55ue").build();
        Optional<CoreError> error = searchPassengerCarValidator.validateIsAirConditionerAvailable(searchVehicleRequest);
        assertFalse(error.isPresent());
    }

    @Test
    void testValidateIsAirConditionerAvailableValidReturnNoError2() {
        searchVehicleRequest = SearchVehicleRequest.builder().hasConditioner("fAlS*e ").build();
        Optional<CoreError> error = searchPassengerCarValidator.validateIsAirConditionerAvailable(searchVehicleRequest);
        assertFalse(error.isPresent());
    }

    @Test
    void testValidateIsAirConditionerAvailableNullReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().hasConditioner(null).build();
        Optional<CoreError> error = searchPassengerCarValidator.validateIsAirConditionerAvailable(searchVehicleRequest);
        assertFalse(error.isPresent());
        assertEquals(Optional.empty(), error);
    }

    @Test
    void testValidateIsAirConditionerAvailableEmptyReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().hasConditioner("").build();
        Optional<CoreError> error = searchPassengerCarValidator.validateIsAirConditionerAvailable(searchVehicleRequest);
        assertTrue(error.isPresent());
        assertEquals("IsAirConditionerAvailable", error.get().getField());
        assertEquals("can`t be empty or blank", error.get().getMessage());
    }

    @Test
    void testValidateIsAirConditionerAvailableBlankReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().hasConditioner(" ").build();
        Optional<CoreError> error = searchPassengerCarValidator.validateIsAirConditionerAvailable(searchVehicleRequest);
        assertTrue(error.isPresent());
        assertEquals("IsAirConditionerAvailable", error.get().getField());
        assertEquals("can`t be empty or blank", error.get().getMessage());
    }

    @Test
    void testValidateIsAirConditionerAvailableNotValidReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().hasConditioner("not valid").build();
        Optional<CoreError> error = searchPassengerCarValidator.validateIsAirConditionerAvailable(searchVehicleRequest);
        assertTrue(error.isPresent());
        assertEquals("IsAirConditionerAvailable", error.get().getField());
        assertEquals("must be either true or false", error.get().getMessage());
    }

    @Test
    void testValidatePassengerAmountIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).passengerAmount(CAR_MAX_PASSENGER_AMOUNT).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidatePassengerAmountNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).passengerAmount(null).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidatePassengerAmountIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).passengerAmount(-1).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + CAR_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountIsZeroReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).passengerAmount(0).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + CAR_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountIsLessThanMinAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).passengerAmount(CAR_MIN_PASSENGER_AMOUNT - 1).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + CAR_MIN_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidatePassengerAmountIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).passengerAmount(CAR_MAX_PASSENGER_AMOUNT + 1).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Passenger amount", errors.get(0).getField());
        assertEquals("can`t be more than " + CAR_MAX_PASSENGER_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateDoorsAmountIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).doorsAmount(CAR_MAX_DOORS_AMOUNT).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDoorsAmountNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).doorsAmount(null).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateDoorsAmountIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).doorsAmount(-1).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + CAR_MIN_DOORS_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateDoorsAmountIsZeroReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).doorsAmount(0).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + CAR_MIN_DOORS_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateDoorsAmountIsLessThanMinAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).doorsAmount(CAR_MIN_DOORS_AMOUNT - 1).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + CAR_MIN_DOORS_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateDoorsAmountIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).doorsAmount(CAR_MAX_DOORS_AMOUNT + 1).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("can`t be more than " + CAR_MAX_DOORS_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testValidateBaggageAmountIsValidReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).baggageAmount(CAR_MAX_BAGGAGE_AMOUNT).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateBaggageAmountNotProvidedIsNullReturnNoError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).baggageAmount(null).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateBaggageAmountIsNegativeReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).baggageAmount(-1).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Baggage amount", errors.get(0).getField());
        assertEquals("can`t be negative", errors.get(0).getMessage());
    }

    @Test
    void testValidateBaggageAmountIsMoreThanMaxAllowedReturnError() {
        searchVehicleRequest = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).baggageAmount(CAR_MAX_BAGGAGE_AMOUNT + 1).build();
        List<CoreError> errors = searchPassengerCarValidator.validate(searchVehicleRequest);
        assertFalse(errors.isEmpty());
        assertEquals("Baggage amount", errors.get(0).getField());
        assertEquals("can`t be more than " + CAR_MAX_BAGGAGE_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    void testIsEnumVehicleValueValidPositive() {
        String testValue = "manual";
        assertTrue(searchPassengerCarValidator.isEnumVehicleValueValid(TransmissionType.getAllEnumValues(), testValue));
    }

    @Test
    void testIsEnumVehicleValueValidNegative() {
        String testValue = "not valid";
        assertFalse(searchPassengerCarValidator.isEnumVehicleValueValid(TransmissionType.getAllEnumValues(), testValue));
    }
}