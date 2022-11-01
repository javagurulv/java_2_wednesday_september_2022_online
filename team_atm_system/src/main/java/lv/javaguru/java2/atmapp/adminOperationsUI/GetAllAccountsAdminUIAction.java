package lv.javaguru.java2.atmapp.adminOperationsUI;

import lv.javaguru.java2.atmapp.core.requests.adminRequests.GetAllAccountsRequest;
import lv.javaguru.java2.atmapp.core.responses.adminResponses.GetAllAccountsResponse;
import lv.javaguru.java2.atmapp.core.services.adminOperations.GetAllAccountsService;


public class GetAllAccountsAdminUIAction implements AdminUIactions {

    private GetAllAccountsService getAllAccountsService;

    public GetAllAccountsAdminUIAction(GetAllAccountsService getAllAccountsService) {
        this.getAllAccountsService = getAllAccountsService;
    }


    @Override
    public void execute() {
        GetAllAccountsRequest request = new GetAllAccountsRequest();
        GetAllAccountsResponse response = getAllAccountsService.execute(request);
        response.getAccounts().forEach(System.out::println);
    }
}
