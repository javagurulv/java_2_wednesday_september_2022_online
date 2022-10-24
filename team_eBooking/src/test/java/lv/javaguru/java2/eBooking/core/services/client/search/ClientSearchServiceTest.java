package lv.javaguru.java2.eBooking.core.services.client.search;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.SearchClientRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.client.SearchClientResponse;
import lv.javaguru.java2.eBooking.core.services.client.ClientSearchService;
import lv.javaguru.java2.eBooking.core.services.validators.ClientValidationResult;
import lv.javaguru.java2.eBooking.core.services.validators.ClientSearchRequestValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientSearchServiceTest {

    @Mock
    private Database database;

    @Mock
    private ClientSearchRequestValidator validator;

    @InjectMocks
    private ClientSearchService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        SearchClientRequest request = new SearchClientRequest(null, null);
        List<CoreError> errors = validator.validate(request);
        errors.add(new CoreError("Email", ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY));
        errors.add(new CoreError("Phone number", ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        SearchClientResponse response = service.execute(request);

        assertTrue(response.hasError());
        assertEquals(response.getErrors().size(), 2);
        assertEquals(response.getErrors().get(0).getField(), "Email");
        assertEquals(response.getErrors().get(0).getClientValidationMessage(),
                ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY);
        assertEquals(response.getErrors().get(1).getField(), "Phone number");
        assertEquals(response.getErrors().get(1).getClientValidationMessage(),
                ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY);

        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void shouldNotReturnErrorWhenSearchingByEmail() {
        SearchClientRequest request = new SearchClientRequest("Email", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Email", "Phone number"));
        Mockito.when(database.findClientByEMail("Email")).thenReturn(clients);
        SearchClientResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getClients().size(), 1);
        assertEquals(response.getClients().get(0).getClientEmail(), "Email");
        assertEquals(response.getClients().get(0).getClientPhoneNumber(), "Phone number");
    }

    @Test
    public void shouldNotReturnErrorWhenSearchingByPhoneNumber() {
        SearchClientRequest request = new SearchClientRequest(null, "Phone number");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Email", "Phone number"));
        Mockito
                .when(database.findClientByPhoneNumber("Phone number"))
                .thenReturn(clients);
        SearchClientResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getClients().size(), 1);
        assertEquals(response.getClients().get(0).getClientEmail(), "Email");
        assertEquals(response.getClients().get(0).getClientPhoneNumber(), "Phone number");
    }

    @Test
    public void shouldNotReturnErrorWhenSearchingByEmailAndPhoneNumber() {
        SearchClientRequest request = new SearchClientRequest("Email", "Phone number");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Email", "Phone number"));
        Mockito
                .when(database.findClientByEmailAndPhoneNumber("Email", "Phone number"))
                .thenReturn(clients);
        SearchClientResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getClients().size(), 1);
        assertEquals(response.getClients().get(0).getClientEmail(), "Email");
        assertEquals(response.getClients().get(0).getClientPhoneNumber(), "Phone number");
    }
}