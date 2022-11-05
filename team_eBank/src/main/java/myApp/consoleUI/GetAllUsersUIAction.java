package myApp.consoleUI;

import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllUsersResponse;
import myApp.core.services.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllUsersUIAction implements UIAction {

    @Autowired
    private GetAllUsersService service;

    @Override
    public void execute() {
        System.out.println("Bank account: ");
        GetAllBankAccountsRequest request = new GetAllBankAccountsRequest();
        GetAllUsersResponse result = service.execute(request);
        result.getUsers().forEach(System.out::println);
    }
}
