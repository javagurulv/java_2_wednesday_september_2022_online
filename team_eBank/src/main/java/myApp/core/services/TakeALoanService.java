package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.database.jpa.JpaUserRepository;
import myApp.core.requests.TakeALoanRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.TakeALoanResponses;
import myApp.core.services.validators.TakeALoanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class TakeALoanService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;
    @Autowired
    private TakeALoanValidator validator;

    public TakeALoanResponses execute(TakeALoanRequest request) {
        List<CoreError> errors = validator.execute(request);
        if (errors.isEmpty()) {
            bankAccountRepository.takeALoan(getUsername(), request.getValue());
            return new TakeALoanResponses(true);
        } else {
            return new TakeALoanResponses(errors);
        }
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return username;
    }
}

