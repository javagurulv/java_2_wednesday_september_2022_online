package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientSearchRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.client.ClientSearchResponse;
import lv.javaguru.java2.eBooking.core.services.validators.ClientSearchValidator;

import java.util.ArrayList;
import java.util.List;

public class ClientSearchService {

    private Database database;
    private ClientSearchValidator validator;


    public ClientSearchService(Database database,
                               ClientSearchValidator validator) {
        this.database = database;
        this.validator = validator;

    }

    public ClientSearchResponse execute(ClientSearchRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ClientSearchResponse(errors, null);
        }
        List<Client> clients = search(request);
        return new ClientSearchResponse(null, clients);
    }

    private List<Client> search(ClientSearchRequest request) {
        List<Client> clients = new ArrayList<>();
        if (request.isEmailProvided() && !request.isPhoneNumberProvided()) {
            clients = database.findClientByEMail(request.getClientEmail());
        }
        if (request.isPhoneNumberProvided() && !request.isEmailProvided()) {
            clients = database.findClientByPhoneNumber(request.getClientPhoneNumber());
        }
        if (request.isEmailProvided() && request.isPhoneNumberProvided()) {
            clients = database.findClientByEmailAndPhoneNumber(request.getClientEmail(), request.getClientPhoneNumber());
        }
        return clients;
    }

}
