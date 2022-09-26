package eBooking.service;

import eBooking.Client;
import eBooking.database.Database;

public class AddClientService {
    private Database database;

    public AddClientService(Database database) {
        this.database = database;
    }

    public void execute(String clientEmail, String clientPhoneNumber) {
        database.saveClient(new Client(clientEmail, clientPhoneNumber));
    }
}
