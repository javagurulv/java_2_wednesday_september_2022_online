package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.requests.GetAllUsersRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.GetAllUsersService;

public class GetAllUsersUIAction implements UIAction {
    private GetAllUsersService getAllUsersService;

    public GetAllUsersUIAction(GetAllUsersService getAllUsersService) {
        this.getAllUsersService = getAllUsersService;
    }

    @Override
    public boolean execute() {
        GetAllUsersRequest request = new GetAllUsersRequest();
        GetAllUsersResponse response =  getAllUsersService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        } else {
            System.out.println("--- Users list start --- ");
            response.getUsers().forEach(System.out::println);
            System.out.println("---  Users list end  --- ");
            return true;
        }
    }
}
