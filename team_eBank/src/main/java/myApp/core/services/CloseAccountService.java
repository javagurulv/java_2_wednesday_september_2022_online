package myApp.core.services;

import myApp.core.database.BankAccountRepository;
import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.CloseAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CloseAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private CloseAccountValidator validator;

    public CloseAccountResponse execute(CloseAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            if (accountNullCheck(request.getPersonalCode())) {
                boolean result = bankAccountRepository.closeAccount(request.getPersonalCode());
                return new CloseAccountResponse(result);
            }
        }
            return new CloseAccountResponse(errors);
    }

    private boolean accountNullCheck(String personalCode) {
        return bankAccountRepository.getAllBankAccounts().stream()
                .filter(b -> b.getPersonalCode().equals(personalCode))
                .anyMatch(b -> b.getBalance() != null);
    }
}
