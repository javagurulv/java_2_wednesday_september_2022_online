package generalPackage.adminOperations;

import generalPackage.adminRequests.DeleteAccountRequest;
import generalPackage.adminResponses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DeleteAccountServiceValidator {

    public List<CoreError> validate(DeleteAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateUserID(request).ifPresent(errors::add);
        validateIDLength(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateUserID(DeleteAccountRequest request) {
        return (Integer.toString(request.getUserIDtoDelete()) == null)
                ? Optional.of(new CoreError("User ID: ", "Can not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIDLength(DeleteAccountRequest request) {
        var idToTest = request.getUserIDtoDelete();
        var iDtoString = String.valueOf(idToTest);
        int numberCount = 0;
        for (int i = 0; i < iDtoString.length(); i++ ){
            numberCount++;
        }
        return (numberCount != 4)
                ? Optional.of(new CoreError("User ID", "Should contain 4 digits"))
                : Optional.empty();
    }
}
