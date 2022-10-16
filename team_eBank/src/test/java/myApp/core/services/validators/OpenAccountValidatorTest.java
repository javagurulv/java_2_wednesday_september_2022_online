package myApp.core.services.validators;

import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OpenAccountValidatorTest {

    private OpenAccountValidator validator = new OpenAccountValidator();

    @Test
    public void testSuccessValidate() {
        OpenAccountRequest request = new OpenAccountRequest("000000-00001");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testShouldReturnErrorAboutWrongPersonalCode() {
        OpenAccountRequest request = new OpenAccountRequest("000000-001");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Personal code", errors.get(0).getField());
        assertEquals("Personal code must not be empty", errors.get(0).getMessage());
    }

    @Test
    public void testShouldReturnErrorAboutEmptyPersonalCode() {
        OpenAccountRequest request = new OpenAccountRequest("");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Personal code", errors.get(0).getField());
        assertEquals("Personal code must not be empty", errors.get(0).getMessage());
    }
}