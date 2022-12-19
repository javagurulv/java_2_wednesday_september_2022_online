package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
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
    private JpaBankAccountRepository bankRepository;
    @Autowired
    private OpenAccountValidator validator;

    public OpenAccountResponse execute(OpenAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            if (accountNullCheck(request.getPersonalCode())) {
                bankRepository.openAccount(request.getPersonalCode());
                if (!accountNullCheck(request.getPersonalCode())) {
                    return new OpenAccountResponse(true);
                }
            }
        }
        return new OpenAccountResponse(errors);
    }

    private boolean accountNullCheck(String personalCode) {
        return bankRepository.findAll().stream()
                .filter(b -> b.getPersonalCode().equals(personalCode))
                .anyMatch(b -> b.getBalance() == null);
    }
}
