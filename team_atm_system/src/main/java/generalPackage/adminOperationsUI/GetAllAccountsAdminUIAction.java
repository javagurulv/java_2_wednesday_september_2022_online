package generalPackage.adminOperationsUI;

import generalPackage.adminOperations.GetAllAccountsService;
import generalPackage.adminRequests.GetAllAccountsRequest;
import generalPackage.adminResponses.GetAllAccountsResponse;


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
