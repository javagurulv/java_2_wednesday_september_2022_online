package myApp.services;

import myApp.BankAccount;
import myApp.database.DataBase;

import java.util.List;

public class GetAllBankAccountsService {

    private DataBase dataBase;

    public GetAllBankAccountsService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public List<BankAccount> execute() {
        return dataBase.getAllBankAccounts();
    }
}
