package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.ClientDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddClientRequest;
import lv.javaguru.java2.rentapp.core.responses.AddClientResponse;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.validators.add_client_validators.ExistedClientCheckValidator;
import lv.javaguru.java2.rentapp.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ExistedClientCheckService {
    @Autowired
    private ExistedClientCheckValidator existedClientCheckValidator;
    @Autowired
    private ClientDatabase clientDatabase;

    public AddClientResponse execute(AddClientRequest request) {
        List<CoreError> errors = existedClientCheckValidator.validate(request);
        if (!errors.isEmpty()) {
            return new AddClientResponse(errors);
        }

        Optional<Long> existedClientIdOpt = getExistingClientId(request);
        return existedClientIdOpt.map(AddClientResponse::new).orElseGet(() -> new AddClientResponse(0L));
    }

    private Optional<Long> getExistingClientId(AddClientRequest request) {
        return Stream.of(checkClientExistenceByPersonalId(request), checkClientExistenceByEmail(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    public Optional<Long> checkClientExistenceByPersonalId(AddClientRequest request) {
        if (clientDatabase.findByPersonalId(request.getPersonalId()).isPresent()) {
            return clientDatabase.findByPersonalId(request.getPersonalId()).map(Client::getId);
        }
        return Optional.empty();
    }

    public Optional<Long> checkClientExistenceByEmail(AddClientRequest request) {
        if (clientDatabase.findByEmail(request.getEmail()).isPresent()) {
            return clientDatabase.findByEmail(request.getEmail()).map(Client::getId);
        }
        return Optional.empty();
    }
}