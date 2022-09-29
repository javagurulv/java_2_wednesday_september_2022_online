package myApp.core.services;

import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.database.DataBase;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.AddBankAccountValidator;

import java.util.List;

public class AddBankAccountService {

    private DataBase dataBase;
    private AddBankAccountValidator validator;

    public AddBankAccountService(DataBase dataBase, AddBankAccountValidator validator) {
        this.dataBase = dataBase;
        this.validator = validator;
    }

    public AddBankAccountResponse execute(AddBankAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            BankAccount bankAccount = new BankAccount(request.getName(), request.getSurname(), request.getPassword()
                    ,Roles.Regular_user, request.getPersonalCode());
            dataBase.addBankAccount(bankAccount);
            return new AddBankAccountResponse(bankAccount);
        } else {
            return new AddBankAccountResponse(errors);
        }
    }
}
