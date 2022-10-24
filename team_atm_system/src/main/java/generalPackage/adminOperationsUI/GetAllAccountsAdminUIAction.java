package generalPackage.adminOperationsUI;

import generalPackage.core.requests.adminRequests.GetAllAccountsRequest;
import generalPackage.core.responses.adminResponses.GetAllAccountsResponse;
import generalPackage.core.services.adminOperations.GetAllAccountsService;


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
