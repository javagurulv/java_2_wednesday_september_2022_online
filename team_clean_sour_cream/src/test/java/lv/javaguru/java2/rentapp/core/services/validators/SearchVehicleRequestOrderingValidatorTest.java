package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SearchVehicleRequestOrderingValidatorTest {

    SearchVehicleRequestOrderingValidator validator;

    @BeforeEach
    void setUp() {
        validator = new SearchVehicleRequestOrderingValidator();
    }

    @Test
    void testValidateReturnListWith1ErrorWhenOrderByIsNull() {
        Ordering ordering = new Ordering(null, "ascending");
        SearchVehicleRequest request = SearchVehicleRequest.builder().ordering(ordering).build();
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("orderBy", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    void testValidateMandatoryOrderByNoError() {
        Ordering ordering = new Ordering("price", "ascending");
        Optional<CoreError> error = validator.validateMandatoryOrderBy(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateMandatoryOrderByReturnsErrorWhenFieldIsBlank() {
        Ordering ordering = new Ordering("", "ascending");
        Optional<CoreError> error = validator.validateMandatoryOrderBy(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderBy", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    void testValidateMandatoryOrderByReturnsErrorWhenFieldIsNull() {
        Ordering ordering = new Ordering(null, "ascending");
        Optional<CoreError> error = validator.validateMandatoryOrderBy(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderBy", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    void testValidateMandatoryOrderDirectionNoError() {
        Ordering ordering = new Ordering("price", "ascending");
        Optional<CoreError> error = validator.validateMandatoryOrderDirection(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateMandatoryOrderDirectionReturnsErrorWhenFieldIsBlank() {
        Ordering ordering = new Ordering("price", "");
        Optional<CoreError> error = validator.validateMandatoryOrderDirection(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderDirection", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    void testValidateMandatoryOrderDirectionReturnsErrorWhenFieldIsNull() {
        Ordering ordering = new Ordering("price", null);
        Optional<CoreError> error = validator.validateMandatoryOrderDirection(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderDirection", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    void testValidateOrderByNoErrorWhenFieldIsPrice() {
        Ordering ordering = new Ordering("price", "ascending");
        Optional<CoreError> error = validator.validateOrderBy(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateOrderByNoErrorWhenFieldIsYear() {
        Ordering ordering = new Ordering("year", "ascending");
        Optional<CoreError> error = validator.validateOrderBy(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateOrderByErrorWhenFieldIsNotValid() {
        Ordering ordering = new Ordering("not valid", "ascending");
        Optional<CoreError> error = validator.validateOrderBy(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderBy", error.get().getField());
        assertEquals("Must contain 'price' or 'year' only!", error.get().getMessage());
    }

    @Test
    void testValidateOrderDirectionNoErrorWhenFieldIsAscending() {
        Ordering ordering = new Ordering("price", "ascending");
        Optional<CoreError> error = validator.validateOrderDirection(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateOrderDirectionNoErrorWhenFieldIsDescending() {
        Ordering ordering = new Ordering("year", "descending");
        Optional<CoreError> error = validator.validateOrderDirection(ordering);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateOrderDirectionErrorWhenFieldIsNotValid() {
        Ordering ordering = new Ordering("price", "not valid");
        Optional<CoreError> error = validator.validateOrderDirection(ordering);
        assertFalse(error.isEmpty());
        assertEquals("orderDirection", error.get().getField());
        assertEquals("Must contain 'ASCENDING' or 'DESCENDING' only!", error.get().getMessage());
    }

}