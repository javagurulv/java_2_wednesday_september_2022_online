package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.database.VehicleDatabaseImpl;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteVehicleByPlateNumberValidatorTest {

    DeleteVehicleByPlateNumberRequestValidator validator;
    VehicleDatabase vehicleDatabase;
    Vehicle vehicle;

    @BeforeEach
    void setUp() {
        vehicle = Mockito.mock(Vehicle.class);
        vehicleDatabase = Mockito.mock(VehicleDatabaseImpl.class);
        validator = new DeleteVehicleByPlateNumberRequestValidator(vehicleDatabase);
    }

    @Test
    void testValidateListReturnErrors() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
    }

    @Test
    void testValidatePlateNumberReturnErrorIfVehicleWithThatPlateNumberIsNotInDatabase() {
        when(vehicle.getPlateNumber()).thenReturn("123");
        when(vehicleDatabase.getAllVehicles()).thenReturn(List.of(vehicle));
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("456");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Plate number", errors.get(0).getField());
        assertEquals("vehicle with this plate number is not present in database", errors.get(0).getMessage());
    }

    @Test
    void testValidatePlateNumberReturnNoErrorIfVehicleWithThatPlateNumberIsInDatabase() {
        when(vehicle.getPlateNumber()).thenReturn("123");
        when(vehicleDatabase.getAllVehicles()).thenReturn(List.of(vehicle));
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("123");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateVehicleByPlateNumberShouldReturnErrorThanNumberIsBlank() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest(" ");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("Plate number", errors.get(0).getField());
        assertEquals("can`t be empty or blank", errors.get(0).getMessage());
        Mockito.verifyNoInteractions(vehicleDatabase);
    }

    @Test
    void testValidateVehicleByPlateNumberShouldReturnErrorThanNumberIsEmpty() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("Plate number", errors.get(0).getField());
        assertEquals("can`t be empty or blank", errors.get(0).getMessage());
        Mockito.verifyNoInteractions(vehicleDatabase);
    }

    @Test
    void testValidateVehicleByPlateNumberShouldReturnErrorThanNumberIsNull() {
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("Plate number", errors.get(0).getField());
        assertEquals("can`t be empty or blank", errors.get(0).getMessage());
        Mockito.verifyNoInteractions(vehicleDatabase);
    }
}