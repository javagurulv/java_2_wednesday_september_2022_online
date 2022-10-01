package eBooking.service;

import eBooking.database.Database;
import eBooking.request.RemoveClientRequest;
import eBooking.response.RemoveClientResponse;

public class RemoveClientService {
    private Database database;

    public RemoveClientService(Database database) {
        this.database = database;
    }

    public RemoveClientResponse execute(RemoveClientRequest removeClientRequest) {
        boolean isClient = database.deleteClientById(removeClientRequest.getRemoveClientId());
        return new RemoveClientResponse(isClient);
    }
}
