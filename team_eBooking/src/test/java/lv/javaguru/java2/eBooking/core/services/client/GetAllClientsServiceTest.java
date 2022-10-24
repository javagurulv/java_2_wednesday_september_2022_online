package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.GetAllClientsRequest;
import lv.javaguru.java2.eBooking.core.responses.client.GetAllClientsResponse;
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
public class GetAllClientsServiceTest {

    @Mock
    private Database database;

    @InjectMocks
    private GetAllClientsService service;

    @Test
    public void shouldGetAListOgClientsFromDatabase(){
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Client email", "Client phone number"));
        Mockito.when(database.getAllClients()).thenReturn(clients);
        GetAllClientsRequest request = new GetAllClientsRequest();
        GetAllClientsResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getClients().size(),1);
        assertEquals(response.getClients().get(0).getClientEmail(),"Client email");
        assertEquals(response.getClients().get(0).getClientPhoneNumber(),"Client phone number");
    }
}