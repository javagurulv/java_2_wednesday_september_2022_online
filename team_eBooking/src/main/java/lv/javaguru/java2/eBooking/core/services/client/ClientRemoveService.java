package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientRemoveRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.client.ClientRemoveResponse;
import lv.javaguru.java2.eBooking.core.services.validators.ClientRemoveValidator;
import lv.javaguru.java2.eBooking.core.services.validators.ClientValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientRemoveService {
    @Autowired
    Database database;
    @Autowired
    ClientRemoveValidator validator;

    public ClientRemoveResponse execute(ClientRemoveRequest request) {
        if (request.getRemoveClientId() == null) {
            CoreError error = new CoreError("Id", ClientValidationResult.CLIENT_ID_MUST_NOT_BE_EMPTY);
            List<CoreError> errors = new ArrayList<>();
            errors.add(error);
            return new ClientRemoveResponse(errors);
        }
        boolean isClientRemoved = database.deleteClientById(request.getRemoveClientId());
        return new ClientRemoveResponse(isClientRemoved);
    }
}
