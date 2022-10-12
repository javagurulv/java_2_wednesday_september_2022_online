package generalPackage.balanceOperations;


import generalPackage.usersRequests.IncreaseBalanceRequest;
import generalPackage.usersResponses.CoreErrorUsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IncreaseBalanceValidator {

    public List<CoreErrorUsers> validate(IncreaseBalanceRequest request) {
        List<CoreErrorUsers> errors = new ArrayList<>();
        validateIDLength(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreErrorUsers> validateIDLength(IncreaseBalanceRequest request) {
        var idToTest = request.getUserID();
        var iDToString = String.valueOf(idToTest);
        int numberCount = 0;
        for (int i = 0; i < iDToString.length(); i++) {
            numberCount++;
        }
        return (numberCount != 4)
                ? Optional.of(new CoreErrorUsers("User ID", "should contain 4 digits"))
                : Optional.empty();
    }
}
