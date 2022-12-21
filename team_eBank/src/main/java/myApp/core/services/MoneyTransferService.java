package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.MoneyTransferResponse;
import myApp.core.services.validators.MoneyTransferValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

    public MoneyTransferResponse execute(MoneyTransferRequest request, String personalCode) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            Optional<BankAccount> bankAccountResult = bankRepository.findAll().stream()
                    .filter(bankAccount -> bankAccount.getPersonalCode().equals(request.getPersonalCode()))
                    .findFirst();
            bankRepository.bankTransfer(personalCode, request.getAnotherPersonalCode(),
                    request.getValue());

            if (bankAccountResult.isPresent()) {
                if (isTransactionDone(bankAccountResult.get())) {
                    return new MoneyTransferResponse(true);
                }
            }
        }
        return new MoneyTransferResponse(errors);
    }

    private boolean isTransactionDone(BankAccount bankAccountResult) {
        return bankRepository.findAll().stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(bankAccountResult.getPersonalCode()))
                .anyMatch(bankAccount -> !Objects.equals(bankAccount.getBalance(), bankAccountResult.getBalance()));
    }
}