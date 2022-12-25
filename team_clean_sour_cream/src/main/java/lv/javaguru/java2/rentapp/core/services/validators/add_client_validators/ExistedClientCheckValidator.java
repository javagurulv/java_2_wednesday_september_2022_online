package lv.javaguru.java2.rentapp.core.services.validators.add_client_validators;

import lv.javaguru.java2.rentapp.core.database.ClientDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddClientRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class ExistedClientCheckValidator {

    @Autowired
    private ClientDatabase clientDatabase;

    private Optional<Long> checkClientExistenceByPersonalId(AddClientRequest request) {
        if (clientDatabase.findByPersonalId(request.getPersonalId()).isPresent()) {
            return clientDatabase.findByPersonalId(request.getPersonalId()).map(Client::getId);
        }
        return Optional.empty();
    }

    private Optional<Long> checkClientExistenceByEmail(AddClientRequest request) {
        if (clientDatabase.findByEmail(request.getEmail()).isPresent()) {
            return clientDatabase.findByEmail(request.getEmail()).map(Client::getId);
        }
        return Optional.empty();
    }

    public Optional<Long> isConflictBetweenExistenceCheckResults(AddClientRequest request) {
        Optional<Long> personalCode = checkClientExistenceByPersonalId(request);
		Optional<Long> email = checkClientExistenceByEmail(request);

		if (personalCode.isPresent() && email.isPresent()) {
			Long persCodeId = personalCode.get();
			Long emailId = email.get();
			return persCodeId.equals(emailId) ? personalCode : Optional.empty();
		}

		return Stream.of(personalCode, email)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.findFirst();


/*
		if (checkClientExistenceByPersonalId(request).isPresent() && checkClientExistenceByEmail(request).isEmpty()) {
            return checkClientExistenceByPersonalId(request);
        } else if (checkClientExistenceByPersonalId(request).isEmpty() && checkClientExistenceByEmail(request).isPresent()) {
            return checkClientExistenceByPersonalId(request);
        } else if (checkClientExistenceByPersonalId(request).isPresent() && checkClientExistenceByEmail(request).isPresent() &&
                checkClientExistenceByPersonalId(request).equals(checkClientExistenceByEmail(request))) {
            return checkClientExistenceByPersonalId(request);
        }
        return Optional.empty();
*/
    }

    public Optional<Long> isClientFoundedByPersonalId(AddClientRequest request) {
        if (checkClientExistenceByPersonalId(request).isPresent()) {
            return checkClientExistenceByPersonalId(request);
        }
        return Optional.empty();
    }

    public Optional<Long> isClientFoundedByEmail(AddClientRequest request) {
        if (checkClientExistenceByEmail(request).isPresent()) {
            return checkClientExistenceByEmail(request);
        }
        return Optional.empty();
    }

    public List<CoreError> validate(AddClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (checkClientExistenceByPersonalId(request).isPresent()) {
            validatePersonalIdNotEmpty(request).ifPresent(errors::add);
            if (validatePersonalIdNotEmpty(request).isEmpty()) {
                validatePersonalIdFormat(request).ifPresent(errors::add);
            }
        }

        return errors;
    }

    private Optional<CoreError> validateDuplicateClientFirstName(AddClientRequest request) {
        Client existedClient = getExistedClientByPersonalIdIfPresent(request);
        if (existedClient != null) {
            if (!existedClient.getFirstName().equals(request.getFirstName())) {
                return Optional.of(new CoreError("First Name", "different from client with same personal ID"));
            }
        }
        return Optional.empty();
    }

    private Client getExistedClientByPersonalIdIfPresent(AddClientRequest request) {
        return clientDatabase.findByPersonalId(request.getPersonalId()).isPresent()
                ? clientDatabase.findByPersonalId(request.getPersonalId()).get()
                : null;
    }

    private Client getExistedClientByEmailIfPresent(AddClientRequest request) {
        return clientDatabase.findByEmail(request.getEmail()).isPresent()
                ? clientDatabase.findByEmail(request.getEmail()).get()
                : null;
    }
}
