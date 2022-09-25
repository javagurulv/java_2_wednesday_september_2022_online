package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;

public class CloseAccountService {

    private DataBase dataBase;


    public CloseAccountService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public CloseAccountResponse execute(CloseAccountRequest request) {
        boolean result = dataBase.closeAccount(request.getPersonalCode());
        return new CloseAccountResponse(result);
    }

}
