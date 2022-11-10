package generalPackage.core.services.adminOperations.adminValidators;

import generalPackage.core.requests.adminRequests.SearchAccountsServiceRequest;
import generalPackage.core.responses.adminResponses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchAccountsServiceValidator {

    public List<CoreError> validate(SearchAccountsServiceRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateNameToSearch(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateNameToSearch(SearchAccountsServiceRequest request) {
        return (request.getUserNameToFind() == null || request.getUserNameToFind().isEmpty())
                ? Optional.of(new CoreError("Name", "can not be empty"))
                : Optional.empty();
    }
}
