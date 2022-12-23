package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.AddBankAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;


@Component
@Transactional
public class AddBankAccountService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;
    @Autowired
    private AddBankAccountValidator validator;

    public AddBankAccountResponse execute(AddBankAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
                BankAccount bankAccount = new BankAccount(request.getName(), request.getSurname(),
                        request.getPersonalCode());
            bankAccountRepository.save(bankAccount);
                return new AddBankAccountResponse(bankAccount);
            }
        return new AddBankAccountResponse(errors);
    }
}
