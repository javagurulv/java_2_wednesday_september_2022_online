package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleRequestOrderingValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchVehicleRequestOrderingValidatorTest {

    SearchVehicleRequestOrderingValidator validator;

    @BeforeEach
    void setUp() {
        validator = new SearchVehicleRequestOrderingValidator();
    }

    @Test
    void testValidateReturnListWith1ErrorWhenOrderByIsNull() {
        Ordering ordering = new Ordering(null, "ASC");
        List<CoreError> errors = validator.validate(ordering);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("orderBy", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    void testValidateReturnsNoErrorWhenOrderingIsValid() {
        Ordering ordering = new Ordering("price", "ASC");
        List<CoreError> error = validator.validate(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateMandatoryOrderByReturnsErrorWhenFieldIsBlank() {
        Ordering ordering = new Ordering(" ", "ASC");
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderBy", error.get(0).getField());
        assertEquals("Must not be empty!", error.get(0).getMessage());
    }

    @Test
    void testValidateMandatoryOrderByReturnsErrorWhenFieldIsEmpty() {
        Ordering ordering = new Ordering("", "ASC");
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderBy", error.get(0).getField());
        assertEquals("Must not be empty!", error.get(0).getMessage());
    }

    @Test
    void testValidateMandatoryOrderByReturnsErrorWhenFieldIsNull() {
        Ordering ordering = new Ordering(null, "ASC");
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderBy", error.get(0).getField());
        assertEquals("Must not be empty!", error.get(0).getMessage());
    }

    @Test
    void testValidateMandatoryOrderDirectionReturnsErrorWhenFieldIsBlank() {
        Ordering ordering = new Ordering("price", " ");
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderDirection", error.get(0).getField());
        assertEquals("Must not be empty!", error.get(0).getMessage());
    }

    @Test
    void testValidateMandatoryOrderDirectionReturnsErrorWhenFieldIsEmpty() {
        Ordering ordering = new Ordering("price", "");
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderDirection", error.get(0).getField());
        assertEquals("Must not be empty!", error.get(0).getMessage());
    }

    @Test
    void testValidateMandatoryOrderDirectionReturnsErrorWhenFieldIsNull() {
        Ordering ordering = new Ordering("price", null);
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderDirection", error.get(0).getField());
        assertEquals("Must not be empty!", error.get(0).getMessage());
    }

    @Test
    void testValidateOrderByErrorWhenFieldIsNotValid() {
        Ordering ordering = new Ordering("not valid", "ASC");
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderBy", error.get(0).getField());
        assertEquals("Must contain 'price' or 'year' only!", error.get(0).getMessage());
    }


    @Test
    void testValidateOrderDirectionErrorWhenFieldIsNotValid() {
        Ordering ordering = new Ordering("price", "not valid");
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderDirection", error.get(0).getField());
        assertEquals("Must contain 'ASC' (ASCENDING) or 'DESC' (DESCENDING) only!", error.get(0).getMessage());
    }

    @Test
    void testValidateOrderByAndOrderDirectionErrorWhenFieldsAreNotNull() {
        Ordering ordering = new Ordering(null, null);
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("\"orderBy\" and \"orderDirection\"", error.get(0).getField());
        assertEquals("Must not be empty!", error.get(0).getMessage());
    }

    @Test
    void testValidateOrderByAndOrderDirectionErrorWhenFieldsAreEmpty() {
        Ordering ordering = new Ordering("", "");
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("\"orderBy\" and \"orderDirection\"", error.get(0).getField());
        assertEquals("Must not be empty!", error.get(0).getMessage());
    }

    @Test
    void testValidateOrderByAndOrderDirectionErrorWhenFieldsAreBlank() {
        Ordering ordering = new Ordering(" ", " ");
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("\"orderBy\" and \"orderDirection\"", error.get(0).getField());
        assertEquals("Must not be empty!", error.get(0).getMessage());
    }

    @Test
    void testValidateOrderByAndOrderDirectionErrorsWhenFieldsIsAreNotValid() {
        Ordering ordering = new Ordering("not valid", "not valid");
        List<CoreError> error = validator.validate(ordering);
        assertEquals(2, error.size());
        assertEquals("orderBy", error.get(0).getField());
        assertEquals("Must contain 'price' or 'year' only!", error.get(0).getMessage());
        assertEquals("orderDirection", error.get(1).getField());
        assertEquals("Must contain 'ASC' (ASCENDING) or 'DESC' (DESCENDING) only!", error.get(1).getMessage());
    }
}