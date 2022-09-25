package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.services.LogoutService;

public class LogoutUIAction implements UIAction {

    private LogoutService logoutService;

    public LogoutUIAction(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @Override
    public boolean execute() {
        logoutService.execute();
        System.out.println("You have been logged out");
        return true;
    }
}
