package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.RemoveBankAccountResponse;
import myApp.core.services.validators.RemoveBankAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class RemoveBankAccountService {

    @Autowired
    private JpaBankAccountRepository bankRepository;
    @Autowired
    private RemoveBankAccountValidator validator;

    public RemoveBankAccountResponse execute(RemoveBankAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
           bankRepository.deleteById(request.getId());
            if (!bankRepository.existsById(request.getId())) {
                return new RemoveBankAccountResponse(true);
            }
        }
        return new RemoveBankAccountResponse(errors);
    }


/*
    public RemoveBankAccountResponse execute(RemoveBankAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            bankRepository.deleteById(request.getId());
            if (!bankRepository.existsById(request.getId())) {
                return new RemoveBankAccountResponse(true);
            }
     //   }
        return new RemoveBankAccountResponse(null);
    }

 */
}


