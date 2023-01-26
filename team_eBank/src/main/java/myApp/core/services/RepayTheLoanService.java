package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.RepayTheLoanRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.RepayTheLoanResponse;
import myApp.core.services.validators.RepayTheLoanValidator;
import myApp.web_ui.security.EncoderAndDecoderPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class RepayTheLoanService {

    @Autowired
    private JpaBankAccountRepository repository;
    @Autowired
    private RepayTheLoanValidator validator;

    public RepayTheLoanResponse execute(RepayTheLoanRequest request) {
        List<CoreError> errors = validator.execute(request);
        if (errors.isEmpty()) {
            repository.withdrawMoney(getUsername(),request.getValue());
            repository.decreaseValueOfDebt(getUsername(), request.getValue());
            checkIsDebtBiggerThanPaymentOrNot();
            return new RepayTheLoanResponse(true);
        }
        return new RepayTheLoanResponse(errors);
    }

    private void checkIsDebtBiggerThanPaymentOrNot() {
        Optional<BankAccount> bankAccount = findBankAccount();
        if (bankAccount.get().getDebt() < 0) {
            repository.takeALoan(getUsername(),
                    Math.abs(bankAccount.get().getDebt()));
            repository.openDebt(getUsername());
        }
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private Optional<BankAccount> findBankAccount() {
        return repository.findAll().stream()
                .filter(b -> b.getPersonalCode().equals(getUsername()))
                .findFirst();
    }
}