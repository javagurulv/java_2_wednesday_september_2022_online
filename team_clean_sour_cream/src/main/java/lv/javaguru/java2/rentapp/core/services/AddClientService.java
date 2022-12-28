package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.ClientDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddClientRequest;
import lv.javaguru.java2.rentapp.core.responses.AddClientResponse;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.ExistedClientCheckResponse;
import lv.javaguru.java2.rentapp.core.services.validators.add_client_validators.ExistedClientCheckValidator;
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
    private ExistedClientCheckService existedClientCheckService;
    @Autowired
    private ClientDatabase clientDatabase;

    public AddClientResponse execute(AddClientRequest request) {
        List<CoreError> addClientErrors = addClientValidator.validate(request);
        if (!addClientErrors.isEmpty()) {
            return new AddClientResponse(addClientErrors);
        }

        Client client = getClient(request);

        ExistedClientCheckResponse existedClientCheckResponse = existedClientCheckService.execute(request);

        if (!existedClientCheckResponse.hasErrors()) {
            return new AddClientResponse(existedClientCheckResponse.getErrors());
        }

        Long newClientId = clientDatabase.save(client);
        client.setId(newClientId);
        return new AddClientResponse(newClientId);
    }

    private Client getClient(AddClientRequest request) {
        return new Client(request.getPersonalId(), request.getFirstName(), request.getLastName(),
                request.getEmail(), request.getPhoneNumber());
    }
}
