package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class VehicleAvailabilityServiceValidatorTest {

    @InjectMocks
    VehicleAvailabilityServiceValidator validator;

    @Test
    void testGoodScenario() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusDays(1).format(formatter);
        String rentEndDate = LocalDate.now().plusDays(5).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder()
                .rentStartDate(rentStartDate).rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateStartDateIsNotPresentIsEmpty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentEndDate = LocalDate.now().plusDays(5).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate("").rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Start date", errors.get(0).getField());
        assertEquals("can't be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateEndDateIsPresentIsEmpty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String rentStartDate = LocalDate.now().plusDays(1).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate(rentStartDate).rentEndDate("").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("End date", errors.get(0).getField());
        assertEquals("can't be empty", errors.get(0).getMessage());
    }

    @Test
    void testValidateStartDateFormatIsNotCorrectReturnError() {
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate("29/02/2023").rentEndDate("10/03/2023").build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Start date", errors.get(0).getField());
        assertEquals("has to be in format dd/MM/yyyy ", errors.get(0).getMessage());
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
        String rentEndDate = LocalDate.now().plusYears(4).format(formatter);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().rentStartDate(rentStartDate).rentEndDate(rentEndDate).build();
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Start date", errors.get(0).getField());
        assertEquals("has to be within one year from now ", errors.get(0).getMessage());
    }
}