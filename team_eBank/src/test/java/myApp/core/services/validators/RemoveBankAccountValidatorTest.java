package myApp.core.services.validators;


import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RemoveBankAccountValidatorTest {

    RemoveBankAccountValidator validator = new RemoveBankAccountValidator();

    @Test
    public void testSuccessValidate() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest(1L);
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testShouldReturnErrorAboutWrongId() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Id", errors.get(0).getField());
        assertEquals("Id must not be empty", errors.get(0).getMessage());
    }
}