package generalPackage.core.services.usersOperations.usersValidators;

import generalPackage.core.domain.Accounts;
import generalPackage.core.requests.usersRequests.DecreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.CoreErrorUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DecreaseBalanceValidator {

    @Autowired
    Accounts accountToCheck;

    public List<CoreErrorUsers> validate(DecreaseBalanceRequest request) {
        List<CoreErrorUsers> errors = new ArrayList<>();
        validateIDLength(request).ifPresent(errors::add);
//        validateAvailableAmount(request).ifPresent(errors::add);
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

//    private Optional<CoreErrorUsers> validateAvailableAmount(DecreaseBalanceRequest request) {
//        int amountAvailable = accountToCheck.getBalance();
//        int amountToDecreace = request.getAmountToDecrease();
//
//        return (amountAvailable < amountToDecreace)
//                ? Optional.of(new CoreErrorUsers("Amount", "is insufficient"))
//                : Optional.empty();
//    }
}
