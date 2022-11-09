package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.apache.commons.validator.GenericValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
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
    void testValidateShouldCheckIsStartDateValidAndInCorrectFormatByGenericValidatorLib() {
        GeneralRentVehicleRequest request = Mockito.mock(GeneralRentVehicleRequest.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Mockito.when(request.getRentStartDate()).thenReturn(LocalDate.now().plusDays(1).format(formatter));
        try (MockedStatic<GenericValidator> genericValidator = Mockito.mockStatic(GenericValidator.class)) {
            validator.validate(request);
            genericValidator.verify(() -> GenericValidator.isDate(request.getRentStartDate(), "dd/MM/yyyy", true), atLeastOnce());
        }
    }

    @Test
    void testValidateShouldCheckIsStartDateValidByGenericValidatorLib() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentEndDate = LocalDate.now().plusMonths(2).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate("29/02/2023").rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Start date", errors.get(0).getField());
        assertEquals("has to be valid and in format dd/MM/yyyy", errors.get(0).getMessage());
    }

    @Test
    void testValidateShouldCheckIsStartDateInCorrectFormatByGenericValidatorLib() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentEndDate = LocalDate.now().plusMonths(2).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate("2/02/23").rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Start date", errors.get(0).getField());
        assertEquals("has to be valid and in format dd/MM/yyyy", errors.get(0).getMessage());
    }

    @Test
    void testValidateShouldReturnErrorWhenStartDateIsInPast() {
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
    void testValidateShouldReturnErrorWhenStartDateIsMoreWhenOneYearForwardFromNow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusYears(3).format(formatter);
        String rentEndDate = LocalDate.now().plusYears(3).plusMonths(1).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate(rentStartDate).rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Start date", errors.get(0).getField());
        assertEquals("has to be within one year from now", errors.get(0).getMessage());
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
    void testValidateShouldCheckIsEndDateValidAndInCorrectFormatByGenericValidatorLib() {
        GeneralRentVehicleRequest request = Mockito.mock(GeneralRentVehicleRequest.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Mockito.when(request.getRentEndDate()).thenReturn(LocalDate.now().plusDays(1).format(formatter));
        try (MockedStatic<GenericValidator> genericValidator = Mockito.mockStatic(GenericValidator.class)) {
            validator.validate(request);
            genericValidator.verify(() -> GenericValidator.isDate(request.getRentEndDate(), "dd/MM/yyyy", true), atLeastOnce());
        }
    }

    @Test
    void testValidateShouldCheckIsEndDateValidByGenericValidatorLib() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusMonths(2).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate(rentStartDate).rentEndDate("10/13/2023").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("End date", errors.get(0).getField());
        assertEquals("has to be valid and in format dd/MM/yyyy", errors.get(0).getMessage());
    }

    @Test
    void testValidateShouldCheckIsEndtDateInCorrectFormatByGenericValidatorLib() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusMonths(2).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate(rentStartDate).rentEndDate("2/02/23").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("End date", errors.get(0).getField());
        assertEquals("has to be valid and in format dd/MM/yyyy", errors.get(0).getMessage());
    }

    @Test
    void testValidateShouldReturnErrorWhenEndDateIsInPast() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusDays(1).format(formatter);
        String rentEndDate = LocalDate.now().minusDays(5).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder()
                .rentStartDate(rentStartDate).rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("End date", errors.get(0).getField());
        assertEquals("can't be before current day", errors.get(0).getMessage());
    }

    @Test
    void testValidateShouldReturnErrorWhenEndDateIsMoreThanTwoMonthsForwardAfterStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusDays(1).format(formatter);
        String rentEndDate = LocalDate.now().plusMonths(2).plusDays(2).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate(rentStartDate).rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("End date", errors.get(0).getField());
        assertEquals("has to be within two months from rent start date", errors.get(0).getMessage());
    }

}