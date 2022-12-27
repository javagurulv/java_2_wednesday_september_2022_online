package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.MoneyTransferResponse;
import myApp.core.services.validators.MoneyTransferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Transactional
public class MoneyTransferService {

    @Autowired
    private JpaBankAccountRepository bankRepository;
    @Autowired
    private MoneyTransferValidator validator;

    public MoneyTransferResponse execute(MoneyTransferRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            bankRepository.bankTransfer(getUsername(), request.getAnotherPersonalCode(),
                    request.getValue());
                    return new MoneyTransferResponse(true);
            }
        return new MoneyTransferResponse(errors);
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return username;
    }
}