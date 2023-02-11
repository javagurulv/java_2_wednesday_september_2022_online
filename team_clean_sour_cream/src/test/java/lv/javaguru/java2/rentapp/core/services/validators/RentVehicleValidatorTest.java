package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.VehicleAvailabilityResponse;
import lv.javaguru.java2.rentapp.core.services.VehicleAvailabilityService;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class RentVehicleValidatorTest {

    @Mock
    private VehicleAvailabilityService vehicleAvailabilityService;

    @InjectMocks
    private RentVehicleValidator validator;

    @Test
    void testShouldReturnNoErrorsWhenRequestIsValid() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("010190-10333")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testShouldReturnErrorWhenIdIsNull() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(null).personalId("010190-10333")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Vehicle id", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenChosenIdIsNotAmongstProvidedOptions() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(10L).personalId("010190-10333")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Vehicle with id 10", errors.get(0).getField());
        assertEquals("was not one of the provided options", errors.get(0).getMessage());
    }


    @Test
    void testShouldReturnErrorsWhenChosenIdIsNotLongerAvailable() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("010190-10333")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of()));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Vehicle with id 1", errors.get(0).getField());
        assertEquals("is not longer available", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenEmailIsNull() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("010190-10333")
                .firstName("test").lastName("test").email(null).phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Email", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenEmailIsBlank() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("010190-10333")
                .firstName("test").lastName("test").email("").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Email", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenEmailFormatIsNotValid() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("010190-10333")
                .firstName("test").lastName("test").email("test").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Email", errors.get(0).getField());
        assertEquals("not valid format (username@domain.com)", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenPersonalIdIsNull() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId(null)
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Personal ID", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenPersonalIdIsEmpty() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Personal ID", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenPersonalIdFormatIsNotValidV1() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("not valid")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Personal ID", errors.get(0).getField());
        assertEquals("Has to be in format 000000-00000", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenPersonalIdFormatIsNotValidV2() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("0000")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Personal ID", errors.get(0).getField());
        assertEquals("Has to be in format 000000-00000", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenPersonalIdFormatIsNotValidV3() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("010i9o-10468")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Personal ID", errors.get(0).getField());
        assertEquals("Has to be in format 000000-00000", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenFirstNameIsNull() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("000000-00000")
                .firstName(null).lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("First name", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenFirstNameIsEmpty() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("000000-00000")
                .firstName("").lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("First name", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenFirstNameContainsRestrictedCharacters() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("000000-00000")
                .firstName("1233214Name!@#$%^@#$*&%_#)").lastName("test").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("First name", errors.get(0).getField());
        assertEquals("cannot contain digits or special characters", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenLastNameIsNull() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("000000-00000")
                .firstName("test").lastName(null).email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Last name", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenLastNameIsEmpty() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("000000-00000")
                .firstName("test").lastName("").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Last name", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenLastNameContainsRestrictedCharacters() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("000000-00000")
                .firstName("test").lastName("1233214Name!@#$%^@#$*&%_#)").email("test@test.com").phoneNumber("+371 200159894").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Last name", errors.get(0).getField());
        assertEquals("cannot contain digits or special characters", errors.get(0).getMessage());
    }


    @Test
    void testShouldReturnErrorWhenPhoneNumberIsNull() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("000000-00000")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber(null).availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Phone number", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenPhoneNumberIsEmpty() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("000000-00000")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Phone number", errors.get(0).getField());
        assertEquals("cannot be empty", errors.get(0).getMessage());
    }

    @Test
    void testShouldReturnErrorWhenPhoneNumberContainsRestrictedCharacters() {
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(vehicle.getId()).thenReturn(1L);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("000000-00000")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("asd!@#").availableVehicles(List.of(vehicle)).build();

        Mockito.when(vehicleAvailabilityService.execute(request, List.of(vehicle))).thenReturn(new VehicleAvailabilityResponse(null, List.of(vehicle)));

        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Phone number", errors.get(0).getField());
        assertEquals("cannot contain letters or special characters", errors.get(0).getMessage());
    }
}