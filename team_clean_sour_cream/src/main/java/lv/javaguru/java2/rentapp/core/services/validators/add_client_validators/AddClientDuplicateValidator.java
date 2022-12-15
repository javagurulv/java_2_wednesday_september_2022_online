package lv.javaguru.java2.rentapp.core.services.validators.add_client_validators;

import lv.javaguru.java2.rentapp.core.database.ClientDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddClientRequest;
import lv.javaguru.java2.rentapp.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AddClientDuplicateValidator {

    @Autowired
    private ClientDatabase clientDatabase;

    public Optional<Long> validate(Client client) {


        return null;
    }
}
