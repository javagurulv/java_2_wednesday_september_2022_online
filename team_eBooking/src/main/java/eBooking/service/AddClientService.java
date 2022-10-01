package eBooking.service;

import eBooking.Client;
import eBooking.database.Database;
import eBooking.request.AddClientRequest;
import eBooking.response.AddClientResponse;

public class AddClientService {
    private Database database;

    public AddClientService(Database database) {
        this.database = database;
    }

    public AddClientResponse execute(AddClientRequest addClientRequest) {
        Client client = new Client(addClientRequest.getClientEmail(), addClientRequest.getClientPhoneNumber());
        database.saveClient(client);
        return new AddClientResponse(client);
    }
}
