package lv.javaguru.java2.eBooking.service;

import lv.javaguru.java2.eBooking.database.Database;

public class RemoveClientService {
    private Database database;

    public RemoveClientService(Database database) {
        this.database = database;
    }

    public void removeClient(Long clientId) {
        database.deleteClientById(clientId);
    }
}
