package myApp.core.services;

import myApp.BankAccount;
import myApp.core.database.DataBase;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;

import java.util.List;

public class GetAllBankAccountsService {

    private DataBase dataBase;

    public GetAllBankAccountsService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public GetAllBankAccountsResponse execute(GetAllBankAccountsRequest request) {
        List<BankAccount> bankAccounts = dataBase.getAllBankAccounts();
        return new GetAllBankAccountsResponse(bankAccounts);
    }
}
