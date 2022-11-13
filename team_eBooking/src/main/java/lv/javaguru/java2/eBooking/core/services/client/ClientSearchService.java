package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.database.ClientRepository;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientSearchRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.client.ClientSearchResponse;
import lv.javaguru.java2.eBooking.core.services.validators.ClientSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ClientSearchService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private ClientSearchValidator validator;

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
            clients = clientRepository.findClientByEMail(request.getClientEmail());
        }
        if (request.isPhoneNumberProvided() && !request.isEmailProvided()) {
            clients = clientRepository.findClientByPhoneNumber(request.getClientPhoneNumber());
        }
        if (request.isEmailProvided() && request.isPhoneNumberProvided()) {
            clients = clientRepository.findClientByEmailAndPhoneNumber(request.getClientEmail(), request.getClientPhoneNumber());
        }
        return clients;
    }

}
