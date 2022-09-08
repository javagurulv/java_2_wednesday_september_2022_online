package myApp.services;

import myApp.BankAccount;
import myApp.database.DataBase;

public class AddBankAccountService {

    private DataBase dataBase;

    public AddBankAccountService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

   public void execute(String name, String surname, int balance) {
        dataBase.addBankAccount(new BankAccount(name, surname, balance));
    }
}
