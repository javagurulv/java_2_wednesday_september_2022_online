package lv.javaguru.java2.tasksScheduler.console_ui;

public class ExitUIAction implements UIAction {
    @Override
    public boolean execute() {
        System.out.println("Good bye!");
        System.exit(0);
        return true;
    }
}
