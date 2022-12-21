package lv.javaguru.java2.eBooking.core.services.client.search;

import lv.javaguru.java2.eBooking.core.database.ClientRepository;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientSearchRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.client.ClientSearchResponse;
import lv.javaguru.java2.eBooking.core.services.client.ClientSearchService;
import lv.javaguru.java2.eBooking.core.services.validators.ClientValidationResult;
import lv.javaguru.java2.eBooking.core.services.validators.ClientSearchValidator;
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
    private ClientRepository clientRepository;

    @Mock
    private ClientSearchValidator validator;

    @InjectMocks
    private ClientSearchService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        ClientSearchRequest request = new ClientSearchRequest(null, null);
        List<CoreError> errors = validator.validate(request);
        errors.add(new CoreError("Email", ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY));
        errors.add(new CoreError("Phone number", ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ClientSearchResponse response = service.execute(request);

        assertTrue(response.hasError());
        assertEquals(response.getErrors().size(), 2);
        assertEquals(response.getErrors().get(0).getField(), "Email");
        assertEquals(response.getErrors().get(0).getClientValidationMessage(),
                ClientValidationResult.EMAIL_MUST_NOT_BE_EMPTY);
        assertEquals(response.getErrors().get(1).getField(), "Phone number");
        assertEquals(response.getErrors().get(1).getClientValidationMessage(),
                ClientValidationResult.PHONE_NUMBER_MUST_NOT_BE_EMPTY);

        Mockito.verifyNoInteractions(clientRepository);
    }

    @Test
    public void shouldNotReturnErrorWhenSearchingByEmail() {
        ClientSearchRequest request = new ClientSearchRequest("Email", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Email", "Phone number"));
        Mockito.when(clientRepository.findClientByEMail("Email")).thenReturn(clients);
        ClientSearchResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getClients().size(), 1);
        assertEquals(response.getClients().get(0).getClientEmail(), "Email");
        assertEquals(response.getClients().get(0).getClientPhoneNumber(), "Phone number");
    }

    @Test
    public void shouldNotReturnErrorWhenSearchingByPhoneNumber() {
        ClientSearchRequest request = new ClientSearchRequest(null, "Phone number");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Email", "Phone number"));
        Mockito
                .when(clientRepository.findClientByPhoneNumber("Phone number"))
                .thenReturn(clients);
        ClientSearchResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getClients().size(), 1);
        assertEquals(response.getClients().get(0).getClientEmail(), "Email");
        assertEquals(response.getClients().get(0).getClientPhoneNumber(), "Phone number");
    }

    @Test
    public void shouldNotReturnErrorWhenSearchingByEmailAndPhoneNumber() {
        ClientSearchRequest request = new ClientSearchRequest("Email", "Phone number");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Email", "Phone number"));
        Mockito
                .when(clientRepository.findClientByEmailAndPhoneNumber("Email", "Phone number"))
                .thenReturn(clients);
        ClientSearchResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getClients().size(), 1);
        assertEquals(response.getClients().get(0).getClientEmail(), "Email");
        assertEquals(response.getClients().get(0).getClientPhoneNumber(), "Phone number");
    }
}