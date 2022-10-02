package lv.javaguru.java2.eBooking.core.service;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.request.RemoveClientRequest;
import lv.javaguru.java2.eBooking.core.response.RemoveClientResponse;

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
