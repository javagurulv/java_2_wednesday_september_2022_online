package myApp.consoleUI;

import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import myApp.core.services.GetAllBankAccountsService;
import myApp.core.database.DataBase;

public class GetAllAccountsUIAction implements UIAction {

    private GetAllBankAccountsService service;
    private GetAllBankAccountsRequest request;

    public GetAllAccountsUIAction(DataBase dataBase) {
        service = new GetAllBankAccountsService(dataBase);
    }

    @Override
    public void execute() {
        System.out.println("Bank account: ");
        GetAllBankAccountsResponse result = service.execute(request);
        System.out.println(result.getBankAccounts());
    }
}
