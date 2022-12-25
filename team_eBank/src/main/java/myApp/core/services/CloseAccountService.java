package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.CloseAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
@Component
@Transactional
public class CloseAccountService {

    @Autowired
    private JpaBankAccountRepository bankRepository;
    @Autowired
    private CloseAccountValidator validator;

    public CloseAccountResponse execute(CloseAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            if (accountNullCheck(request.getPersonalCode())) {
                bankRepository.closeAccount(request.getPersonalCode());
                    return new CloseAccountResponse(true);
            }
        }
            return new CloseAccountResponse(errors);
    }

    private boolean accountNullCheck(String personalCode) {
        return bankRepository.findAll().stream()
                .filter(b -> b.getPersonalCode().equals(personalCode))
                .anyMatch(b -> b.getBalance() == 0);
    }
}