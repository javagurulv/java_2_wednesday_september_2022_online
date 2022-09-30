package myApp.core.services;

import lombok.AllArgsConstructor;
import myApp.core.database.DataBase;
import myApp.core.domain.BankAccount;
import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.SearchBankAccountResponse;
import myApp.core.services.validators.SearchBankAccountValidator;

import java.util.ArrayList;
import java.util.List;

public class SearchBankAccountService {

    private DataBase dataBase;
    private SearchBankAccountValidator validator;

    public SearchBankAccountService(DataBase dataBase, SearchBankAccountValidator validator) {
        this.dataBase = dataBase;
        this.validator = validator;
    }

    public SearchBankAccountResponse execute(SearchBankAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        List<BankAccount> bankAccounts = new ArrayList<>();
        if (!errors.isEmpty()) {
            return new SearchBankAccountResponse(errors,null);
        } else {
            if (request.nameNullCheck() && !request.surnameNullCheck() && !request.personalCodeNullCheck()) {
                bankAccounts = dataBase.findByName(request.getName());
            }
            if (!request.nameNullCheck() && request.surnameNullCheck() && !request.personalCodeNullCheck()) {
                bankAccounts = dataBase.findBySurname(request.getSurname());
            }
            if (!request.nameNullCheck() && !request.surnameNullCheck() && request.personalCodeNullCheck()) {
                bankAccounts = dataBase.findByPersonalCode(request.getPersonalCode());
            }
            if (request.nameNullCheck() && request.surnameNullCheck() && !request.personalCodeNullCheck()) {
                bankAccounts = dataBase.findByNameAndSurname(request.getName(), request.getSurname());
            }
            if (request.nameNullCheck() && !request.surnameNullCheck() && request.personalCodeNullCheck()) {
                bankAccounts = dataBase.findByNameAndPersonalCode(request.getName(), request.getPersonalCode());
            }
            if (!request.nameNullCheck() && request.surnameNullCheck() && request.personalCodeNullCheck()) {
                bankAccounts = dataBase.findBySurnameAndPersonalCode(request.getSurname(), request.getPersonalCode());
            }
            if (request.nameNullCheck() && request.surnameNullCheck() && request.personalCodeNullCheck()) {
                bankAccounts = dataBase.findByNameAndSurnameAndPersonalCode(request.getName(), request.getSurname(),
                        request.getPersonalCode());
            }
        }
        return new SearchBankAccountResponse(null,bankAccounts);
    }

}