package myApp.core.services;

import myApp.core.database.BankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.domain.User;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.AddUserRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.AddBankAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AddBankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AddBankAccountValidator validator;

    public AddBankAccountResponse execute(AddBankAccountRequest request, AddUserRequest userRequest) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            BankAccount bankAccount = new BankAccount(request.getName(), request.getSurname(),
                    "Roles.Regular_user", request.getPersonalCode());
            User user = new User(userRequest.getPersonalCode(), userRequest.getPassword());
            bankAccountRepository.addBankAccount(bankAccount, user);
            return new AddBankAccountResponse(bankAccount);
        }
        return new AddBankAccountResponse(errors);
    }
}
