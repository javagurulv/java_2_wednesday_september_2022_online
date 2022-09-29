package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.domain.BankAccount;
import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;

import java.util.Optional;

public class SeeYourAccountService {

    private DataBase dataBase;

    public SeeYourAccountService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public SeeYourAccountResponse execute(SeeYourAccountRequest request) {
        Optional<BankAccount> bankAccount = dataBase.seeYourAccount(request.getPersonalCode());
        return new SeeYourAccountResponse(bankAccount);
    }
}
