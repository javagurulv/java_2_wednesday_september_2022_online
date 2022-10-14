package myApp.consoleUI;

import myApp.dependency_injection.DIComponent;

@DIComponent
public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("GoodBye!");
        System.exit(0);
    }
}
