package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.requests.GetAllUsersNameRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersNameResponse;
import lv.javaguru.java2.tasksScheduler.services.GetAllUsersNamesService;

public class GetAllUsersNamesUIAction implements UIAction {

    private GetAllUsersNamesService getAllUsersNamesService;

    public GetAllUsersNamesUIAction(GetAllUsersNamesService getAllUsersNamesService) {
        this.getAllUsersNamesService = getAllUsersNamesService;
    }

    @Override
    public boolean execute() {
        GetAllUsersNameRequest request = new GetAllUsersNameRequest();
        GetAllUsersNameResponse response = getAllUsersNamesService.execute(request);

        if (response.hasErrors()) {
            System.out.println("Registration failed");
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        }
        else {
            System.out.println("--- Users list start --- ");
            response.getUserNames().forEach(System.out::println);
            System.out.println("---  Users list end  --- ");
            return true;
        }
    }
}
