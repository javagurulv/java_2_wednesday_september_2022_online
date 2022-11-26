package myApp.consoleUI;

//@Component
public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("GoodBye!");
        System.exit(0);
    }
}
