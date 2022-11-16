package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientAddRequest;
import lv.javaguru.java2.eBooking.core.responses.client.ClientAddResponse;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.services.validators.ClientAddValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ClientAddService {

    @Autowired
    Database database;
    @Autowired
    private ClientAddValidator validator;

    public ClientAddResponse execute(ClientAddRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ClientAddResponse(errors);
        }
        Client client = new Client(request.getClientEmail(), request.getClientPhoneNumber());

        database.saveClient(client);
        return new ClientAddResponse(client);
    }
}
