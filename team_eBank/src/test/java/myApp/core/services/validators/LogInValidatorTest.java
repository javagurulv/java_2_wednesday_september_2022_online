package myApp.core.services.validators;

import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LogInValidatorTest {

    private LogInValidator validator = new LogInValidator();

    @Test
    public void testSuccessValidate() {
        LogInRequest request = new LogInRequest("000000-00001","password");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testShouldReturnErrorAboutWrongPersonalCode() {
        LogInRequest request = new LogInRequest("000000-01","password");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Personal code",errors.get(0).getField());
        assertEquals("Personal code may contains only numbers and cannot be empty",errors.get(0).getMessage());
    }

    @Test
    public void testShouldReturnErrorAboutEmptyPersonalCode() {
        LogInRequest request = new LogInRequest("","password");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Personal code",errors.get(0).getField());
        assertEquals("Personal code may contains only numbers and cannot be empty",errors.get(0).getMessage());
    }

    @Test
    public void testShouldReturnErrorAboutPassword() {
        LogInRequest request = new LogInRequest("000000-00001","");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Password",errors.get(0).getField());
        assertEquals("Password cannot be empty",errors.get(0).getMessage());
    }
}