package lv.javaguru.java2.eBooking.core.services.client.search;

import lv.javaguru.java2.eBooking.core.requests.client_request.ClientSearchRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.services.validators.ClientValidationResult;
import lv.javaguru.java2.eBooking.core.services.validators.ClientSearchValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClientSearchValidatorTest {
    ClientSearchValidator validator = new ClientSearchValidator();

    @Test
    public void shouldNotReturnErrorWhenEmailIsProvided() {
        ClientSearchRequest request = new ClientSearchRequest("email: ", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);

    }

    @Test
    public void shouldNotReturnErrorWhenPhoneNumberIsProvided() {
        ClientSearchRequest request = new ClientSearchRequest(null, "phone number: ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }

    @Test
    public void shouldNotReturnErrorWhenEmailAndPhoneNumberAreProvided() {
        ClientSearchRequest request = new ClientSearchRequest("email: ", "phone number: ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }
    @Test
    public void shouldReturnErrorWhenEmailAndPhoneNumberAreNotProvided() {
        ClientSearchRequest request = new ClientSearchRequest(null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),2);
        assertEquals(errors.get(0).getField(),"email: ");
        assertEquals(errors.get(0).getClientValidationMessage(), ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY);
        assertEquals(errors.get(1).getField(), "phone number: ");
        assertEquals(errors.get(1).getClientValidationMessage(),ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY);
    }
}