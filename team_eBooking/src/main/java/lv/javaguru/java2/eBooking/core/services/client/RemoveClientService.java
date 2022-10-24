package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.client_request.RemoveClientRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.client.RemoveClientResponse;
import lv.javaguru.java2.eBooking.core.services.validators.ClientValidationResult;

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
