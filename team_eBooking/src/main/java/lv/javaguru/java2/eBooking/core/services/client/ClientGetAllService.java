package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientGetAllRequest;
import lv.javaguru.java2.eBooking.core.responses.client.ClientsGetAllResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientGetAllService {
    @Autowired
    private Database database;

    public ClientsGetAllResponse execute(ClientGetAllRequest request) {
        List<Client> clientList = database.getAllClients();
        return new ClientsGetAllResponse(clientList);
    }
}
