package myApp.consoleUI;

import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import myApp.core.services.GetAllBankAccountsService;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class GetAllBankAccountsUIAction implements UIAction {
    @Autowired
    private GetAllBankAccountsService service;

    @Override
    public void execute() {
        System.out.println("Bank account: ");
        GetAllBankAccountsRequest request = new GetAllBankAccountsRequest();
        GetAllBankAccountsResponse result = service.execute(request);
        result.getBankAccounts().forEach(System.out::println);
    }
}
