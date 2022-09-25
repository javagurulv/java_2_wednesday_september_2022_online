package myApp.core.services;
import myApp.BankAccount;
import myApp.core.database.DataBase;
import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.AddAccountResponse;


public class OpenAccountService {

    private DataBase dataBase;

    public OpenAccountService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public AddAccountResponse execute(OpenAccountRequest request) {
        BankAccount bankAccount = dataBase.openAccount(request.getPersonalCode());
        return new AddAccountResponse(bankAccount);
    }
}

