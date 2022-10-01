package eBooking.service;

import eBooking.database.Database;

public class RemoveClientService {
    private Database database;

    public RemoveClientService(Database database) {
        this.database = database;
    }

    public void removeClient(Long clientId) {
        database.deleteClientById(clientId);
    }
}
