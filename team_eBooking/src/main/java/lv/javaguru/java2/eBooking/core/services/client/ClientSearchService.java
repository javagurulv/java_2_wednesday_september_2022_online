package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.SearchClientRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.client.SearchClientResponse;
import lv.javaguru.java2.eBooking.core.services.validators.ClientSearchRequestValidator;

import java.util.ArrayList;
import java.util.List;

public class ClientSearchService {

    private Database database;
    private ClientSearchRequestValidator validator;


    public ClientSearchService(Database database,
                               ClientSearchRequestValidator validator) {
        this.database = database;
        this.validator = validator;

    }

    public SearchClientResponse execute(SearchClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchClientResponse(errors, null);
        }
        List<Client> clients = search(request);
        return new SearchClientResponse(null, clients);
    }

    private List<Client> search(SearchClientRequest request) {
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
