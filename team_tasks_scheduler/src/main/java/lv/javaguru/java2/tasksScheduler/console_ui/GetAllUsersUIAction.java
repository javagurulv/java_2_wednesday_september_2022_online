package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.services.menu_services.GetAllUsersService;

public class GetAllUsersUIAction implements UIAction {
    private GetAllUsersService getAllUsersService;

    public GetAllUsersUIAction(GetAllUsersService getAllUsersService) {
        this.getAllUsersService = getAllUsersService;
    }

    @Override
    public boolean execute() {
        System.out.println("--- Users list start --- ");
        getAllUsersService.execute().forEach(System.out::println);
        System.out.println("---  Users list end  --- ");
        return true;
    }
}
