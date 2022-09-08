package myApp.consoleUI;

import myApp.services.GetAllBankAccountsService;
import myApp.database.DataBase;

public class GetAllAccountsUIAction implements UIAction {

    private GetAllBankAccountsService service;

    public GetAllAccountsUIAction(DataBase dataBase) {
        service = new GetAllBankAccountsService(dataBase);
    }

    @Override
    public void execute() {
        System.out.println("Bank accounts: ");
        System.out.println(service.execute());
    }
}
