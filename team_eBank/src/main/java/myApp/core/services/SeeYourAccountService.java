package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.domain.BankAccount;
import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;

public class SeeYourAccountService {

    private DataBase dataBase;

    public SeeYourAccountService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public SeeYourAccountResponse execute(SeeYourAccountRequest request) {
        BankAccount bankAccount = dataBase.seeYourAccount(request.getPersonalCode());
        return new SeeYourAccountResponse(bankAccount);
    }
}
