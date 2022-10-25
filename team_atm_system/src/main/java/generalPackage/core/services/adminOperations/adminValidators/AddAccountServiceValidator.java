package generalPackage.core.services.adminOperations.adminValidators;

import generalPackage.core.requests.adminRequests.AddAccountRequest;
import generalPackage.core.responses.adminResponses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AddAccountServiceValidator {

    public List<CoreError> validate(AddAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateUserName(request).ifPresent(errors::add);
        validateUserID(request).ifPresent(errors::add);
        validateIDLength(request).ifPresent(errors::add);

        return errors;
    }

    private Optional<CoreError> validateUserName(AddAccountRequest request) {
        return (request.getUserName() == null || request.getUserName().isEmpty())
                ? Optional.of(new CoreError("Name", "can't be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateUserID(AddAccountRequest request) {

        return (Integer.toString(request.getUserId()) == null)
                ? Optional.of(new CoreError("User ID", "can not be empty"))
                : Optional.empty();
//
    }

    private Optional<CoreError> validateIDLength(AddAccountRequest request) {
        var idToTest = request.getUserId();
        var idToTestString = String.valueOf(idToTest);
        int numberCount = 0;
        for (int i = 0; i < idToTestString.length(); i++) {
            numberCount++;
        }
        return (numberCount != 4)
                ? Optional.of(new CoreError("User ID", "should contain 4 digits"))
                : Optional.empty();
    }

//    Unfinished:

    private Optional<CoreError> validateUserIDduplicates(AddAccountRequest request) {
        return Optional.empty();
    }
}
