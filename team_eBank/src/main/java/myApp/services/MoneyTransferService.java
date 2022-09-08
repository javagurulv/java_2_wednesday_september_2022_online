package myApp.services;

import myApp.database.DataBase;

public class MoneyTransferService {

    private DataBase dataBase;

    public MoneyTransferService(DataBase dataBase) {
        this.dataBase = dataBase;
    }
    public void execute(Long userID, int amount, Long anotherAccount) {
        dataBase.bankTransfer(userID, amount, anotherAccount);
    }
}
