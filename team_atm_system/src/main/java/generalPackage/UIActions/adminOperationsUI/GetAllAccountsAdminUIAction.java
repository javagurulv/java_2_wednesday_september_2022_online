package generalPackage.UIActions.adminOperationsUI;

import generalPackage.core.requests.adminRequests.GetAllAccountsRequest;
import generalPackage.core.requests.adminRequests.Ordering;
import generalPackage.core.responses.adminResponses.GetAllAccountsResponse;
import generalPackage.core.services.adminOperations.GetAllAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class GetAllAccountsAdminUIAction implements AdminUIactions {

@Autowired
private GetAllAccountsService getAllAccountsService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select  orderBy (name||balance): ");
        String orderBy = scanner.nextLine();

        System.out.println("Select orderDirection (ASC || DESC): ");
        String orderDirection = scanner.nextLine();

        Ordering ordering = new Ordering(orderBy, orderDirection);

        GetAllAccountsRequest request = new GetAllAccountsRequest(ordering);
        GetAllAccountsResponse response = getAllAccountsService.execute(request);

        response.getAccounts().forEach(System.out::println);
    }
}
