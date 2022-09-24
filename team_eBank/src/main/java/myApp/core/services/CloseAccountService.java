package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;

public class CloseAccountService {
    // Connect UserService
    private DataBase dataBase;

    public CloseAccountService(DataBase dataBase, UserService userService) {
        this.dataBase = dataBase;
    }

    public CloseAccountResponse execute(CloseAccountRequest request) {
        boolean result = dataBase.closeAccount(request.getPersonalCode());
        return new CloseAccountResponse(result);
    }
}
