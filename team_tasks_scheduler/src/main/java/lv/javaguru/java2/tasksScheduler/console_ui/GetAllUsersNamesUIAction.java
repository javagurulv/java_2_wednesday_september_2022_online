package lv.javaguru.java2.tasksScheduler.console_ui;

//import lv.javaguru.java2.tasksScheduler.requests.GetAllUsersNamesRequest;


import lv.javaguru.java2.tasksScheduler.requests.GetAllUsersRequest;
//import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersNamesResponse;
import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersResponse;
//import lv.javaguru.java2.tasksScheduler.services.menu_services.GetAllUsersNamesService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllUsersNamesUIAction implements UIAction {

    @Autowired
    private GetAllUsersService getAllUsersService;

    @Override
    public boolean execute() {

        GetAllUsersRequest request = new GetAllUsersRequest();
        GetAllUsersResponse response = getAllUsersService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        }
        if (response.getUsersNames().isEmpty()) {
            System.out.println("There are no users to display.");
        } else {
            System.out.println("--- Users list start --- ");
            response.getUsersNames().forEach(System.out::println);
            System.out.println("---  Users list end  --- ");
        }
        return true;
    }
}
