package lv.javaguru.java2.atmapp.adminServicesUI;

import lv.javaguru.java2.atmapp.adminServices.GetAllAccountsService;
import lv.javaguru.java2.atmapp.requests.adminRequests.GetAllAccountsRequest;
import lv.javaguru.java2.atmapp.responses.adminResponses.GetAllAccountsResponse;

public class GetAllAccountsAdminUIAction implements AdminUIactions {

private GetAllAccountsService getAllAccountsService;

    public GetAllAccountsAdminUIAction(GetAllAccountsService getAllAccountsService) {
        this.getAllAccountsService = getAllAccountsService;
    }


    @Override
    public void execute() {
        System.out.println("Account list : ");
        GetAllAccountsRequest request = new GetAllAccountsRequest();
        GetAllAccountsResponse response = getAllAccountsService.execute(request);
        response.getAccounts().forEach(System.out::println);
        System.out.println("The end of account's list.");
    }
}
