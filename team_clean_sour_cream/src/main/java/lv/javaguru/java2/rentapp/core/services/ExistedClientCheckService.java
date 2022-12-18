package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.ClientDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddClientRequest;
import lv.javaguru.java2.rentapp.core.responses.AddClientResponse;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.validators.add_client_validators.ExistedClientCheckValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ExistedClientCheckService {
    @Autowired
    private ExistedClientCheckValidator existedClientCheckValidator;
    @Autowired
    private ClientDatabase clientDatabase;

    public AddClientResponse execute(AddClientRequest request) {
        List<CoreError> errors = existedClientCheckValidator.isClientExistByPersonalId(request);
        if (!errors.isEmpty()) {
            return new AddClientResponse(errors);
        }

        Optional<Long> clientId = existedClientCheckValidator.isClientExistByPersonalId(client);
        if (clientId.isPresent()) {
            return new AddClientResponse(clientId.get());
        }

        Long newClientId = clientDatabase.save(client);
        client.setId(newClientId);
        return new AddClientResponse(newClientId);
    }
}