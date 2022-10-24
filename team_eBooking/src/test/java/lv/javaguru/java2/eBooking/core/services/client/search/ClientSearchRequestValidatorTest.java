package lv.javaguru.java2.eBooking.core.services.client.search;

import lv.javaguru.java2.eBooking.core.requests.client_request.SearchClientRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.services.validators.ClientValidationResult;
import lv.javaguru.java2.eBooking.core.services.validators.ClientSearchRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClientSearchRequestValidatorTest {
    ClientSearchRequestValidator validator = new ClientSearchRequestValidator();

    @Test
    public void shouldNotReturnErrorWhenEmailIsProvided() {
        SearchClientRequest request = new SearchClientRequest("email: ", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);

    }

    @Test
    public void shouldNotReturnErrorWhenPhoneNumberIsProvided() {
        SearchClientRequest request = new SearchClientRequest(null, "phone number: ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }

    @Test
    public void shouldNotReturnErrorWhenEmailAndPhoneNumberAreProvided() {
        SearchClientRequest request = new SearchClientRequest("email: ", "phone number: ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }
    @Test
    public void shouldReturnErrorWhenEmailAndPhoneNumberAreNotProvided() {
        SearchClientRequest request = new SearchClientRequest(null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),2);
        assertEquals(errors.get(0).getField(),"email: ");
        assertEquals(errors.get(0).getClientValidationMessage(), ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY);
        assertEquals(errors.get(1).getField(), "phone number: ");
        assertEquals(errors.get(1).getClientValidationMessage(),ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY);
    }
}