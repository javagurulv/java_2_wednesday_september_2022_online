package generalPackage.core.services.adminOperations.adminValidators;


import generalPackage.core.requests.adminRequests.FindUserByIDRequest;
import generalPackage.core.responses.adminResponses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FindUserByIDServiceValidator {

    public List<CoreError> validate(FindUserByIDRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateIDLength(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateIDLength(FindUserByIDRequest request) {
        var idToTest = request.getUserIDtoFind();
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
