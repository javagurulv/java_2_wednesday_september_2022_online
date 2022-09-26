package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DeleteVehicleByPlateNumberValidatorTest {

    DeleteVehicleByPlateNumberValidator deleteVehicleByPlateNumberValidator;

    @BeforeEach
    void setUp() {
        deleteVehicleByPlateNumberValidator = new DeleteVehicleByPlateNumberValidator();
    }

    @Test
    void testValidateListOfErrors() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("");
        List<CoreError> errors = deleteVehicleByPlateNumberValidator.validate(request);
        assertEquals(1, errors.size());
    }

    @Test
    void testValidateVehicleByPlateNumberShouldReturnErrorThanNumberIsBlank() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest(" ");
        Optional<CoreError> errorOptional = deleteVehicleByPlateNumberValidator.validateVehicleByPlateNumber(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Plate number", errorOptional.get().getField());
        assertEquals("can`t be empty or blank", errorOptional.get().getMessage());
    }

    @Test
    void testValidateVehicleByPlateNumberShouldReturnErrorThanNumberIsEmpty() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("");
        Optional<CoreError> errorOptional = deleteVehicleByPlateNumberValidator.validateVehicleByPlateNumber(request);
        assertTrue(errorOptional.isPresent());
        assertEquals("Plate number", errorOptional.get().getField());
        assertEquals("can`t be empty or blank", errorOptional.get().getMessage());
    }

//    @Test
//    void testValidateVehicleByPlateNumberShouldReturnErrorThanNumberIsNull() {
//        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest(null);
//        Optional<CoreError> errorOptional = deleteVehicleByPlateNumberValidator.validateVehicleByPlateNumber(request);
//        assertTrue(errorOptional.isPresent());
//        assertEquals("Plate number", errorOptional.get().getField());
//        assertEquals("can`t be empty or blank", errorOptional.get().getMessage());
//    }

    @Test
    void testValidateVehicleByPlateNumberShouldReturnNoError() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("number");
        Optional<CoreError> errorOptional = deleteVehicleByPlateNumberValidator.validateVehicleByPlateNumber(request);
        assertTrue(errorOptional.isEmpty());
    }
}