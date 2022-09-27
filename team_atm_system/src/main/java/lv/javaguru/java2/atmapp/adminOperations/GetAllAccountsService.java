package lv.javaguru.java2.atmapp.adminOperations;


import lv.javaguru.java2.atmapp.Accounts;
import lv.javaguru.java2.atmapp.database.Database;

import java.util.List;

public class GetAllAccountsService {

    private Database database;

    public GetAllAccountsService(Database database) {
        this.database = database;
    }

    public List<Accounts> execute (){
        return database.getAllAccounts();
    }
}
