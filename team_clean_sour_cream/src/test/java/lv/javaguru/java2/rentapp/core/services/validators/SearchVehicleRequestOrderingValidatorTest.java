package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
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
    void testValidateMandatoryOrderByNoError() {
        Ordering ordering = new Ordering("price", "ASC");
        List<CoreError> error = validator.validate(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateMandatoryOrderByReturnsErrorWhenFieldIsBlank() {
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
    void testValidateMandatoryOrderDirectionNoError() {
        Ordering ordering = new Ordering("price", "ASC");
        List<CoreError> error = validator.validate(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateMandatoryOrderDirectionReturnsErrorWhenFieldIsBlank() {
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
    void testValidateOrderByNoErrorWhenFieldIsPrice() {
        Ordering ordering = new Ordering("price", "ASC");
        List<CoreError> error = validator.validate(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateOrderByNoErrorWhenFieldIsYear() {
        Ordering ordering = new Ordering("year", "ASC");
        List<CoreError> error = validator.validate(ordering);
        assertTrue(error.isEmpty());
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
    void testValidateOrderDirectionNoErrorWhenFieldIsAscending() {
        Ordering ordering = new Ordering("price", "ASC");
        List<CoreError> error = validator.validate(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateOrderDirectionNoErrorWhenFieldIsDescending() {
        Ordering ordering = new Ordering("year", "DESC");
        List<CoreError> error = validator.validate(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateOrderDirectionErrorWhenFieldIsNotValid() {
        Ordering ordering = new Ordering("price", "not valid");
        List<CoreError> error = validator.validate(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderDirection", error.get(0).getField());
        assertEquals("Must contain 'ASC' (ASCENDING) or 'DESC' (DESCENDING) only!", error.get(0).getMessage());
    }

}