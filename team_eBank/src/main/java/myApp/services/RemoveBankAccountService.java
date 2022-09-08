package myApp.services;

import myApp.database.DataBase;

public class RemoveBankAccountService {
    private DataBase dataBase;

    public RemoveBankAccountService (DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void execute(Long id) {
        dataBase.deleteBankAccount(id);
    }
}
