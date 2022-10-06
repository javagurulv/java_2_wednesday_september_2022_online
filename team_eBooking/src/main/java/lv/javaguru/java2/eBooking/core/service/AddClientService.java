package lv.javaguru.java2.eBooking.core.service;

import lv.javaguru.java2.eBooking.Client;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.request.AddClientRequest;
import lv.javaguru.java2.eBooking.core.response.AddClientResponse;
import lv.javaguru.java2.eBooking.core.response.CoreError;

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
