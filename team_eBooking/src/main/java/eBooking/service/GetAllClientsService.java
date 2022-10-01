package eBooking.service;

import eBooking.Client;
import eBooking.database.Database;
import eBooking.request.GetAllClientsRequest;
import eBooking.response.GetAllClientsResponse;

import java.util.List;

public class GetAllClientsService {

    private Database database;

    public GetAllClientsService(Database database) {
        this.database = database;
    }

    public GetAllClientsResponse execute(GetAllClientsRequest getAllClientsRequest) {
        List<Client> clientList = database.getAllClients();
        return new GetAllClientsResponse(clientList);
    }
}
