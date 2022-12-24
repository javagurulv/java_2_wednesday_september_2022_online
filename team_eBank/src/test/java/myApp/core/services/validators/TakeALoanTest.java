package myApp.core.services.validators;

import myApp.core.requests.TakeALoanRequest;
import myApp.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TakeALoanTest {

    private TakeALoanValidator validator = new TakeALoanValidator();

    @Test
    public void successTest(){
        TakeALoanRequest request = new TakeALoanRequest(100);
        List<CoreError> errors = validator.execute(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrorAboutValue(){
        TakeALoanRequest request = new TakeALoanRequest(null);
        List<CoreError> errors = validator.execute(request);
        assertFalse(errors.isEmpty());
    }
}
