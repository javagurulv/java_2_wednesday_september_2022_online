package apartment.console_ui;

public class ExitUIUIAction implements UIAction {


@Override
    public void execute () {
        System.out.println("Goodbye!");
        System.exit(0);
    }

}
