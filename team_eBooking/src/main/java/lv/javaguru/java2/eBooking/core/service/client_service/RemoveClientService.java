package lv.javaguru.java2.eBooking.core.service.client_service;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.request.RemoveClientRequest;
import lv.javaguru.java2.eBooking.core.response.CoreError;
import lv.javaguru.java2.eBooking.core.response.RemoveClientResponse;

import java.util.ArrayList;
import java.util.List;

public class RemoveClientService {
    private Database database;

    public RemoveClientService(Database database) {
        this.database = database;
    }

    public RemoveClientResponse execute(RemoveClientRequest request) {
        if (request.getRemoveClientId() == null) {
            CoreError error = new CoreError("Id", ClientValidationResult.CLIENT_ID_MUST_NOT_BE_EMPTY);
            List<CoreError> errors = new ArrayList<>();
            errors.add(error);
            return new RemoveClientResponse(errors);
        }
        boolean isClientRemoved = database.deleteClientById(request.getRemoveClientId());
        return new RemoveClientResponse(isClientRemoved);
    }
}
