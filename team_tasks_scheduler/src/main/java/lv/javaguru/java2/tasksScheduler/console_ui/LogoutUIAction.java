package lv.javaguru.java2.tasksScheduler.console_ui;

public class LogoutUIAction implements UIAction {
    @Override
    public boolean execute() {
        System.out.println("You have been logged out");
        return true;
    }
}
