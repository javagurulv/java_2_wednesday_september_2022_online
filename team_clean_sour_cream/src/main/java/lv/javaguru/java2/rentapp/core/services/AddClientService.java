package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.ClientDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddClientRequest;
import lv.javaguru.java2.rentapp.core.responses.AddClientResponse;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.validators.add_client_validators.AddClientDuplicateValidator;
import lv.javaguru.java2.rentapp.core.services.validators.add_client_validators.AddClientValidator;
import lv.javaguru.java2.rentapp.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddClientService {
    @Autowired
    private AddClientValidator addClientValidator;

    @Autowired
    private AddClientDuplicateValidator addClientDuplicateValidator;
    @Autowired
    private ClientDatabase clientDatabase;

    public AddClientResponse execute(AddClientRequest request) {
        List<CoreError> errors = addClientValidator.validate(request);
        if (!errors.isEmpty()) {
            return new AddClientResponse(errors);
        }
        Optional<Long> clientId = addClientDuplicateValidator.validate(request);
        if (clientId.isPresent()) {
            return new AddClientResponse(clientId.get());
        }

        Client client = getClient(request);
        Long newClientId = clientDatabase.save(client);
        client.setId(newClientId);
        return new AddClientResponse(newClientId);
    }

    private Client getClient(AddClientRequest request) {
        return new Client(request.getPersonalId(), request.getFirstName(), request.getLastName(),
                request.getEmail(), request.getPhoneNumber());
    }
}
