package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.Paging;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.validators.PagingValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PagingValidatorTest {

    PagingValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PagingValidator();
    }

    @Test
    void testValidateReturnNoErrorWhenPagingIsValid() {
        Paging paging = new Paging(1, 1);
        List<CoreError> errors = validator.validate(paging);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidatePageNumberReturnErrorWhenPageNumberIsZero() {
        Paging paging = new Paging(0, 1);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals("pageNumber", errors.get(0).getField());
        assertEquals("Must be greater than 0!", errors.get(0).getMessage());
    }

    @Test
    void testValidatePageNumberReturnErrorWhenPageNumberIsNegative() {
        Paging paging = new Paging(-1, 1);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals("pageNumber", errors.get(0).getField());
        assertEquals("Must be greater than 0!", errors.get(0).getMessage());
    }

    @Test
    void testValidatePageSizeReturnErrorWhenPageSizeIsZero() {
        Paging paging = new Paging(1, 0);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals("pageSize", errors.get(0).getField());
        assertEquals("Must be greater than 0!", errors.get(0).getMessage());
    }

    @Test
    void testValidatePageSizeReturnErrorWhenPageSizeNegative() {
        Paging paging = new Paging(1, -1);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals("pageSize", errors.get(0).getField());
        assertEquals("Must be greater than 0!", errors.get(0).getMessage());
    }

    @Test
    void testValidateReturnsErrorPageSizeNull() {
        Paging paging = new Paging(1, null);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals("pageSize", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    void testValidateReturnsErrorPageNumberNull() {
        Paging paging = new Paging(null, 1);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals("pageNumber", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    void testValidateReturnsTwoErrorsWhenPageNumberAndSizeAreNull() {
        Paging paging = new Paging(null, null);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(2, errors.size());
        assertEquals("pageNumber", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
        assertEquals("pageSize", errors.get(1).getField());
        assertEquals("Must not be empty!", errors.get(1).getMessage());
    }

}