package eBooking.console_ui;

public class ExitApplicationUIAction implements UIAction {

    public void execute() {
        System.out.println("Application closed");
        System.exit(0);
    }
}
