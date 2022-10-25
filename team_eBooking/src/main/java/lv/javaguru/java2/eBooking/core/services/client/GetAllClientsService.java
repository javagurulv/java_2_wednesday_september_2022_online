package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.client_request.GetAllClientsRequest;
import lv.javaguru.java2.eBooking.core.responses.client.GetAllClientsResponse;

import java.util.List;

public class GetAllClientsService {

    private Database database;

    public GetAllClientsService(Database database) {
        this.database = database;
    }

    public GetAllClientsResponse execute(GetAllClientsRequest request) {
        List<Client> clientList = database.getAllClients();
        return new GetAllClientsResponse(clientList);
    }
}
