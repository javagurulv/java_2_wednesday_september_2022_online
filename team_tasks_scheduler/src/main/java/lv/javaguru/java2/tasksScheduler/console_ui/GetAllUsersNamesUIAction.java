package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.services.GetAllUsersNamesService;

public class GetAllUsersNamesUIAction implements UIAction {

    private GetAllUsersNamesService getAllUsersNamesService;

    public GetAllUsersNamesUIAction(GetAllUsersNamesService getAllUsersNamesService) {
        this.getAllUsersNamesService = getAllUsersNamesService;
    }

    @Override
    public boolean execute() {
        System.out.println("--- Users list start --- ");
        getAllUsersNamesService.execute().forEach(System.out::println);
        System.out.println("---  Users list end  --- ");
        return true;
    }
}
