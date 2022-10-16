package lv.javaguru.java2.eBooking.core.services.client.add;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.client_request.AddClientRequest;
import lv.javaguru.java2.eBooking.core.responses.client.AddClientResponse;
import lv.javaguru.java2.eBooking.core.responses.CoreError;

import java.util.List;

public class AddClientService {
    private Database database;
    private AddClientValidator validator;

    public AddClientService(Database database, AddClientValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddClientResponse execute(AddClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddClientResponse(errors);
        }
        Client client = new Client(request.getClientEmail(), request.getClientPhoneNumber());
        database.saveClient(client);
        return new AddClientResponse(client);
    }
}
