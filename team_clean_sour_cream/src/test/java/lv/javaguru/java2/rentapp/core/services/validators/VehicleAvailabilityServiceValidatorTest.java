package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.apache.commons.validator.GenericValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;

@ExtendWith(MockitoExtension.class)
class VehicleAvailabilityServiceValidatorTest {

    @InjectMocks
    VehicleAvailabilityServiceValidator validator;

    @Test
    void testValidateGoodScenarioShouldReturnNoErrors() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusDays(1).format(formatter);
        String rentEndDate = LocalDate.now().plusDays(5).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder()
                .rentStartDate(rentStartDate).rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateStartDateIsNotPresentIsNull() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentEndDate = LocalDate.now().plusDays(5).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Start date", errors.get(0).getField());
        assertEquals("can't be empty or not present", errors.get(0).getMessage());
    }

    @Test
    void testValidateStartDateIsNotPresentIsEmpty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentEndDate = LocalDate.now().plusDays(5).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate("").rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Start date", errors.get(0).getField());
        assertEquals("can't be empty or not present", errors.get(0).getMessage());
    }

    @Test
    void testValidateStartDateIsNotPresentIsBlank() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentEndDate = LocalDate.now().plusDays(5).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate(" ").rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Start date", errors.get(0).getField());
        assertEquals("can't be empty or not present", errors.get(0).getMessage());
    }

    @Test
    void testValidateEndDateIsNotPresentIsNull() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusDays(1).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate(rentStartDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("End date", errors.get(0).getField());
        assertEquals("can't be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateEndDateIsNotPresentIsEmpty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusDays(1).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate(rentStartDate).rentEndDate("").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("End date", errors.get(0).getField());
        assertEquals("can't be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateEndDateIsNotPresentIsBlank() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusDays(1).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate(rentStartDate).rentEndDate(" ").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("End date", errors.get(0).getField());
        assertEquals("can't be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateShouldCheckStartDateForCorrectFormatByGenericValidator() {
        GeneralRentVehicleRequest request = Mockito.mock(GeneralRentVehicleRequest.class);
        GenericValidator genericValidator = Mockito.mock(GenericValidator.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Mockito.when(request.getRentStartDate()).thenReturn(LocalDate.now().plusDays(1).format(formatter));
        Mockito.verify(genericValidator, atLeastOnce());
        GenericValidator.isDate(request.getRentStartDate(), "dd/MM/yyyy", true);
    }

    @Test
    void testValidateEndDateFormatIsNotCorrectReturnError() {
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate("25/02/2023").rentEndDate("10/13/2023").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("End date", errors.get(0).getField());
        assertEquals("has to be in format dd/MM/yyyy ", errors.get(0).getMessage());
    }

    @Test
    void testValidateStartDateNotInPast() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().minusDays(1).format(formatter);
        String rentEndDate = LocalDate.now().plusDays(5).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder()
                .rentStartDate(rentStartDate).rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Start date", errors.get(0).getField());
        assertEquals("can't be before current day", errors.get(0).getMessage());
    }

    @Test
    void testValidateEndDateNotInPast() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusDays(1).format(formatter);
        String rentEndDate = LocalDate.now().minusDays(5).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder()
                .rentStartDate(rentStartDate).rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(2, errors.size());
        assertEquals("End date", errors.get(0).getField());
        assertEquals("can't be before current day", errors.get(0).getMessage());
        assertEquals("can't be before start date", errors.get(1).getMessage());
    }

    @Test
    void testValidateStartDateIsNotToFarReturnError() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusYears(3).format(formatter);
        String rentEndDate = LocalDate.now().plusYears(3).plusMonths(1).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate(rentStartDate).rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Start date", errors.get(0).getField());
        assertEquals("has to be within one year from now ", errors.get(0).getMessage());
    }
}