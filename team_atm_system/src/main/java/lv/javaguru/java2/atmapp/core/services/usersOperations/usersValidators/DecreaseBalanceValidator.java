package lv.javaguru.java2.atmapp.core.services.usersOperations.usersValidators;

import lv.javaguru.java2.atmapp.core.requests.usersRequests.DecreaseBalanceRequest;
import lv.javaguru.java2.atmapp.core.responses.usersResponses.CoreErrorUsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DecreaseBalanceValidator {

    public List<CoreErrorUsers> validate(DecreaseBalanceRequest request) {
        List<CoreErrorUsers> errors = new ArrayList<>();
        validateIDLength(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreErrorUsers> validateIDLength(DecreaseBalanceRequest request) {
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
