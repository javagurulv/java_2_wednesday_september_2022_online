package lv.javaguru.java2.eBooking.core.services.client_service.search;

import lv.javaguru.java2.eBooking.core.requests.client_request.SearchClientRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.services.client_service.add.ClientValidationResult;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ClientSearchRequestValidatorTest {
    ClientSearchRequestValidator validator = new ClientSearchRequestValidator();

    @Test
    public void shouldNotReceiveErrorWhenClientEmailProvided() {
        SearchClientRequest request = new SearchClientRequest("yevgeniy.tolks@gmail.com" , null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }

    @Test
    public void shouldNotReceiveErrorWhenClientPhoneNumberIsProvided() {
        SearchClientRequest request = new SearchClientRequest(null , "0037126146341");
       List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }

    @Test
    public void shouldNotReceiveErrorWhenClientEmailAndPhoneNumberIsProvided() {
        SearchClientRequest request = new SearchClientRequest("yevgeniy.tolks@gmail.com" , "0037126395758");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }


    @Test
    public void shouldReceiveErrorWhenClientPhoneNumberAreNotProvided() {
        SearchClientRequest request = new SearchClientRequest(null , null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),2);
        assertEquals(errors.get(0).getField(),"email: ");
        assertEquals(errors.get(0).getClientValidationMessage(), ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY);
        assertEquals(errors.get(1).getField(),"phone number: ");
        assertEquals(errors.get(1).getClientValidationMessage(),ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY);
    }
}
