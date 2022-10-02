package lv.javaguru.java2.eBooking.core.service;

import lv.javaguru.java2.eBooking.Client;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.request.AddClientRequest;
import lv.javaguru.java2.eBooking.core.response.AddClientResponse;

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
