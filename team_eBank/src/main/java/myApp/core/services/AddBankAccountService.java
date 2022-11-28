package myApp.core.services;

import myApp.core.database.BankRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.AddUserRequest;
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
    private BankRepository bankRepository;
    @Autowired
    private AddBankAccountValidator validator;

    public AddBankAccountResponse execute(AddBankAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
           // if (duplicateCheck(request, userRequest)) {

                BankAccount bankAccount = new BankAccount(request.getName(), request.getSurname(),
                        request.getPersonalCode());
             //   User user = new User(userRequest.getLogin(), userRequest.getPassword(),"Role_User");
                bankRepository.addBankAccount(bankAccount);
                return new AddBankAccountResponse(bankAccount);
            }
      //  }
        return new AddBankAccountResponse(errors);
    }

    private boolean duplicateCheck(AddBankAccountRequest accountRequest, AddUserRequest userRequest) {
        boolean bankAccountResult = bankRepository.getAllBankAccounts().stream()
                .anyMatch(bankAccount -> bankAccount.getPersonalCode().equals(accountRequest.getPersonalCode()));
        boolean userResult = bankRepository.getAllUsers().stream()
                .anyMatch(user -> user.getPersonalCode().equals(userRequest.getLogin()));
        return !bankAccountResult && !userResult;
    }
}
