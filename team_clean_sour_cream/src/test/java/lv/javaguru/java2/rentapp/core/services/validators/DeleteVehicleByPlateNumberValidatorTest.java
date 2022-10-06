package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeleteVehicleByPlateNumberValidatorTest {

    DeleteVehicleByPlateNumberValidator validator;
    Database database;

    @BeforeEach
    void setUp() {
        database = new InMemoryDatabaseImpl();
        validator = new DeleteVehicleByPlateNumberValidator(database);
    }

    @Test
    void testValidateListOfErrors() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
    }

    @Test
    void testValidatePlateNumberReturnErrorIfVehicleWithThatPlateNumberIsNotInDatabase() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("Not in database");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Plate number", errors.get(0).getField());
        assertEquals("vehicle with this plate number is not present in database", errors.get(0).getMessage());
    }

    @Test
    void testValidateVehicleByPlateNumberShouldReturnErrorThanNumberIsBlank() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest(" ");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("Plate number", errors.get(0).getField());
        assertEquals("can`t be empty or blank", errors.get(0).getMessage());
    }

    @Test
    void testValidateVehicleByPlateNumberShouldReturnErrorThanNumberIsEmpty() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("Plate number", errors.get(0).getField());
        assertEquals("can`t be empty or blank", errors.get(0).getMessage());
    }

    @Test
    void testValidateVehicleByPlateNumberShouldReturnErrorThanNumberIsNull() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("Plate number", errors.get(0).getField());
        assertEquals("can`t be empty or blank", errors.get(0).getMessage());
    }
}