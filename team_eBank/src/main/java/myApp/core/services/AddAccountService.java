package myApp.core.services;
import myApp.BankAccount;
import myApp.core.database.DataBase;
import myApp.core.requests.AddAccountRequest;
import myApp.core.responses.AddAccountResponse;


public class AddAccountService {

    private DataBase dataBase;

    public AddAccountService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public AddAccountResponse execute(AddAccountRequest request) {
        BankAccount bankAccount = dataBase.addAccount(request.getPersonalCode());
        return new AddAccountResponse(bankAccount);
    }
}

