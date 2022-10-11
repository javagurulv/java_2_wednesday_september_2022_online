package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.requests.GetAllUsersNamesRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersNamesResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.GetAllUsersNamesService;

public class GetAllUsersNamesUIAction implements UIAction {

    private GetAllUsersNamesService getAllUsersNamesService;

    public GetAllUsersNamesUIAction(GetAllUsersNamesService getAllUsersNamesService) {
        this.getAllUsersNamesService = getAllUsersNamesService;
    }

    @Override
    public boolean execute() {
        GetAllUsersNamesRequest request = new GetAllUsersNamesRequest();
        GetAllUsersNamesResponse response = getAllUsersNamesService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        }
        if (response.getUserNames().isEmpty()) {
            System.out.println("There are no users to display.");
        } else {
            System.out.println("--- Users list start --- ");
            response.getUserNames().forEach(System.out::println);
            System.out.println("---  Users list end  --- ");
        }
        return true;
    }
}
