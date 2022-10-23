package lv.javaguru.java2.eBooking.core.services.client.search;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.client_request.SearchClientRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.client.SearchClientResponse;
import lv.javaguru.java2.eBooking.core.services.client.add.ClientValidationResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith (MockitoJUnitRunner.class)
public class ClientSearchServiceTest {

    @Mock
    private Database database;

    @Mock
    private ClientSearchRequestValidator validator;

    @InjectMocks

    private ClientSearchService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails(){
        SearchClientRequest request = new SearchClientRequest(null,null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Email",ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY));
        errors.add(new CoreError("Phone number", ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchClientResponse response = service.execute(request);
        assertTrue(response.hasError());

    }
}