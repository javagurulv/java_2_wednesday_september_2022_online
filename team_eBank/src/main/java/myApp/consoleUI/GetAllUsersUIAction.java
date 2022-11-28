package myApp.consoleUI;

import myApp.core.requests.GetAllUsersRequest;
import myApp.core.responses.GetAllUsersResponse;
import myApp.core.services.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class GetAllUsersUIAction implements UIAction {

    @Autowired
    private GetAllUsersService service;

    @Override
    public void execute() {
        System.out.println("Users: ");
        GetAllUsersRequest request = new GetAllUsersRequest();
        GetAllUsersResponse result = service.execute(request);
        result.getUsers().forEach(System.out::println);
    }
}
