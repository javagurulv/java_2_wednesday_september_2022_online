package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientAddRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.client.ClientAddResponse;
import lv.javaguru.java2.eBooking.core.services.validators.ClientAddValidator;
import lv.javaguru.java2.eBooking.core.services.validators.ClientValidationResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AddClientServiceTest {

    @Mock
    private Database database;
    @Mock
    private ClientAddValidator validator;
    @InjectMocks
    private ClientAddService service;

    @Test
    public void shouldReturnResponseWithErrorWhenValidatorFails() {
        ClientAddRequest request = new ClientAddRequest(null, null);
        Mockito
                .when(validator.validate(request))
                .thenReturn(List.of(new CoreError("client email",
                        ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY)));
        ClientAddResponse response = service.execute(request);

        assertTrue(response.hasError());
    }

    @Test
    public void shouldNotInvokeDatabaseWhenValidatorFails() {
        ClientAddRequest request = new ClientAddRequest(null, null);

        Mockito.when(validator.validate(request)).thenReturn(List.of(
                new CoreError("client email",
                        ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY),
                new CoreError("Client phone number",
                        ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY)));
        ClientAddResponse response = service.execute(request);
        assertEquals(response.getErrors().size(), 2);
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldReturnResponseWithoutErrorWhenRequestIsValid() {
        ClientAddRequest request = new ClientAddRequest("Client email", "Client phone number");
        Mockito
                .when(validator.validate(request))
                .thenReturn(List.of());
        ClientAddResponse response = service.execute(request);
        assertFalse(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithAClientWhenRequestIsValid() {
        ClientAddRequest request = new ClientAddRequest("Client email", "Client phone number");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        ClientAddResponse response = service.execute(request);
        assertNotNull(response.getNewClient());
        assertEquals(response.getNewClient().getClientEmail(), request.getClientEmail());
        assertEquals(response.getNewClient().getClientPhoneNumber(), request.getClientPhoneNumber());
    }
}