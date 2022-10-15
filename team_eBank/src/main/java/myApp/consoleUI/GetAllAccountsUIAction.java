package myApp.consoleUI;

import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import myApp.core.services.GetAllBankAccountsService;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

@DIComponent
public class GetAllAccountsUIAction implements UIAction {
    @DIDependency
    private GetAllBankAccountsService service;

    @Override
    public void execute() {
        System.out.println("Bank account: ");
        GetAllBankAccountsRequest request = new GetAllBankAccountsRequest();
        GetAllBankAccountsResponse result = service.execute(request);
        result.getBankAccounts().forEach(System.out::println);
    }
}
