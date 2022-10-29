package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientGetAllRequest;
import lv.javaguru.java2.eBooking.core.responses.client.ClientsGetAllResponse;
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
public class ClientGetAllServiceTest {

    @Mock
    private Database database;

    @InjectMocks
    private ClientGetAllService service;

    @Test
    public void shouldGetAListOgClientsFromDatabase(){
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Client email", "Client phone number"));
        Mockito.when(database.getAllClients()).thenReturn(clients);
        ClientGetAllRequest request = new ClientGetAllRequest();
        ClientsGetAllResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getClients().size(),1);
        assertEquals(response.getClients().get(0).getClientEmail(),"Client email");
        assertEquals(response.getClients().get(0).getClientPhoneNumber(),"Client phone number");
    }
}