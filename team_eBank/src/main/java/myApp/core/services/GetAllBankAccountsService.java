package myApp.core.services;

import myApp.BankAccount;
import myApp.core.database.DataBase;
import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.GetAllBankAccountsResponse;

import java.util.Map;

public class GetAllBankAccountsService {

    private DataBase dataBase;

    public GetAllBankAccountsService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public GetAllBankAccountsResponse execute(SeeYourAccountRequest request) {
        Map<String, BankAccount> bankAccounts = dataBase.getAllBankAccountsMap();
        return new GetAllBankAccountsResponse(bankAccounts);
    }
}
