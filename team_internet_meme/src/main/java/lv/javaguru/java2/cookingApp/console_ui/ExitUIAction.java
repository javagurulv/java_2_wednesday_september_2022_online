package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;


@DIComponent
public class ExitUIAction implements UIAction{
    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
