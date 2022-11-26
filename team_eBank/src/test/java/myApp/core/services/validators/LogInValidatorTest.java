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
    public void testShouldReturnErrorAboutLogin() {
        LogInRequest request = new LogInRequest("","password");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("Login",errors.get(0).getField());
    }

    @Test
    public void testShouldReturnErrorAboutPassword() {
        LogInRequest request = new LogInRequest("000000-00001","");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Password",errors.get(0).getField());
        assertEquals("Password cannot be empty",errors.get(0).getMessage());
    }
}