package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.requests.GetAllUsersRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.GetAllUsersService;

@DIComponent
public class GetAllUsersUIAction implements UIAction {
    @DIDependency private GetAllUsersService getAllUsersService;

    @Override
    public boolean execute() {

        GetAllUsersRequest request = new GetAllUsersRequest();
        GetAllUsersResponse response =  getAllUsersService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        }
        if (response.getUsers().isEmpty()) {
            System.out.println("There are no users to display.");
        }
        else {
            System.out.println("--- Users list start --- ");
            response.getUsers().forEach(System.out::println);
            System.out.println("---  Users list end  --- ");

        }
        return true;
    }
}
