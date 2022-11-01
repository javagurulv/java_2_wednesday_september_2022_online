package generalPackage.adminOperationsUI;

import generalPackage.core.requests.adminRequests.SearchAccountsServiceRequest;
import generalPackage.core.responses.adminResponses.SearchAccountsServiceResponse;
import generalPackage.core.services.adminOperations.SearchAccountsService;
import generalPackage.dependencyInjection.DIComponent;
import generalPackage.dependencyInjection.DIDependency;

import java.util.Scanner;

@DIComponent
public class SearchAccountsAdminUIAction implements AdminUIactions {

    @DIDependency
    private SearchAccountsService searchAccountsService;

//    public SearchAccountsAdminUIAction(SearchAccountsService searchAccountsService) {
//        this.searchAccountsService = searchAccountsService;
//    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input name to search: ");
        String userName = scanner.nextLine();

        SearchAccountsServiceRequest request = new SearchAccountsServiceRequest(userName);
        SearchAccountsServiceResponse response = searchAccountsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Accounts were not found" + coreError.getField() + coreError.getMessage()));
        } else {
            response.getAccounts().forEach(System.out::println);
        }
    }
}
