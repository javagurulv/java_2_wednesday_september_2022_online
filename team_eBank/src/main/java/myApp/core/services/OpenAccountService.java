package myApp.core.services;

import myApp.core.database.BankRepository;
import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.services.validators.OpenAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class OpenAccountService {
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private OpenAccountValidator validator;

    public OpenAccountResponse execute(OpenAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            if (accountNullCheck(request.getPersonalCode())) {
                boolean result = bankRepository.openAccount(request.getPersonalCode());
                return new OpenAccountResponse(result);
            }
        }
        return new OpenAccountResponse(errors);
    }

    private boolean accountNullCheck(String personalCode) {
        return bankRepository.getAllBankAccounts().stream()
                .filter(b -> b.getPersonalCode().equals(personalCode))
                .anyMatch(b -> b.getBalance() == null);
    }
}

