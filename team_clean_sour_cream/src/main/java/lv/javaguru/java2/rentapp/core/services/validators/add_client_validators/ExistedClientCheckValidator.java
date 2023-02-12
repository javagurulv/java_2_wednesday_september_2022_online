package lv.javaguru.java2.rentapp.core.services.validators.add_client_validators;

import lv.javaguru.java2.rentapp.core.database.ClientDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddClientRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.ExistedClientCheckService;
import lv.javaguru.java2.rentapp.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class ExistedClientCheckValidator {

    @Autowired
    private ClientDatabase clientDatabase;

    @Autowired
    private ExistedClientCheckService exClientChkService;

    public List<CoreError> validate(AddClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isExitedClientPresentedByPersonalIdAndNotByEmail(request)
                || isExitedClientPresentedByEmailAndNotByPersonalId(request)
                || isExitedClientPresentedByPersonalIdAndEmail(request) && !isConflictBetweenClientExistenceCheckResults(request)) {
            validateDuplicateClientFirstName(request).ifPresent(errors::add);
            validateDuplicateClientLastName(request).ifPresent(errors::add);
        }
        if (isExitedClientPresentedByPersonalIdAndEmail(request) && isConflictBetweenClientExistenceCheckResults(request)) {
            errors.add(new CoreError("Personal ID and Email", "already exist in other different client`s records"));
        }
        return errors;
    }

    private boolean isExitedClientPresentedByEmailAndNotByPersonalId(AddClientRequest request) {
        return exClientChkService.checkClientExistenceByPersonalId(request).isEmpty()
                && exClientChkService.checkClientExistenceByEmail(request).isPresent();
    }

    private boolean isExitedClientPresentedByPersonalIdAndNotByEmail(AddClientRequest request) {
        return exClientChkService.checkClientExistenceByPersonalId(request).isPresent()
                && exClientChkService.checkClientExistenceByEmail(request).isEmpty();
    }

    private boolean isExitedClientPresentedByPersonalIdAndEmail(AddClientRequest request) {
        return exClientChkService.checkClientExistenceByPersonalId(request).isPresent()
                && exClientChkService.checkClientExistenceByEmail(request).isPresent();
    }

    private boolean isConflictBetweenClientExistenceCheckResults(AddClientRequest request) {
        return !Objects.equals(exClientChkService.checkClientExistenceByPersonalId(request).get(),
                exClientChkService.checkClientExistenceByEmail(request).get());
    }

    private Optional<CoreError> validateDuplicateClientFirstName(AddClientRequest request) {
        if (getExistedClientById(request).isPresent()) {
            Client existedClient = getExistedClientById(request).get();
            if (!existedClient.getFirstName().equals(request.getFirstName())) {
                return Optional.of(new CoreError("First Name", "differs from the first name of existing client" +
                        " record(s) with same personal ID and/or Email"));
            }
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateDuplicateClientLastName(AddClientRequest request) {
        if (getExistedClientById(request).isPresent()) {
            Client existedClient = getExistedClientById(request).get();
            if (!existedClient.getLastName().equals(request.getLastName())) {
                return Optional.of(new CoreError("Last Name", "differs from the last name of existing client" +
                        " record(s) with same personal ID and/or Email"));
            }
        }
        return Optional.empty();
    }

    private Optional<Client> getExistedClientById(AddClientRequest request) {
        Optional<Long> existedClientIdOpt = Stream.of(exClientChkService.checkClientExistenceByPersonalId(request),
                        exClientChkService.checkClientExistenceByEmail(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
        return existedClientIdOpt.isPresent() ? clientDatabase.getById(existedClientIdOpt.get()) : Optional.empty();
    }

//    public Optional<Long> isConflictBetweenExistenceCheckResults(AddClientRequest request) {
//        Optional<Long> optIdByPersonalIdCheckResult = checkClientExistenceByPersonalId(request);
//        Optional<Long> optIdByEmailCheckResult = checkClientExistenceByEmail(request);
//
//        if (optIdByPersonalIdCheckResult.isPresent() && optIdByEmailCheckResult.isPresent()) {
//            Long idByPersonalIdCheckResult = optIdByPersonalIdCheckResult.get();
//            Long idByEmailCheckResult = optIdByEmailCheckResult.get();
//            return idByPersonalIdCheckResult.equals(idByEmailCheckResult) ? optIdByPersonalIdCheckResult : Optional.empty();
//        }
//
//        return Stream.of(optIdByPersonalIdCheckResult, optIdByEmailCheckResult)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .findFirst();
//    }

}
