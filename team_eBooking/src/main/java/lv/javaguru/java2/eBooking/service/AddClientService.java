package lv.javaguru.java2.eBooking.service;

import lv.javaguru.java2.eBooking.Client;
import lv.javaguru.java2.eBooking.database.Database;

public class AddClientService {
    private Database database;

    public AddClientService(Database database) {
        this.database = database;
    }

    public void execute(String clientEmail, String clientPhoneNumber) {
        database.saveClient(new Client(clientEmail, clientPhoneNumber));
    }
}
