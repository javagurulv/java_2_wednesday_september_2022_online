package lv.javaguru.java2.rentapp.core.services.validators.add_client_validators;

import lv.javaguru.java2.rentapp.core.database.ClientDatabase;
import lv.javaguru.java2.rentapp.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddClientDuplicateValidator {

    @Autowired
    private ClientDatabase clientDatabase;

    public Optional<Long> validate(Client client) {
        if (clientDatabase.findByPersonalId(client.getPersonalId()).isPresent()) {
            return clientDatabase.findByPersonalId(client.getPersonalId()).map(Client::getId);
        }
        return Optional.empty();
    }
}
