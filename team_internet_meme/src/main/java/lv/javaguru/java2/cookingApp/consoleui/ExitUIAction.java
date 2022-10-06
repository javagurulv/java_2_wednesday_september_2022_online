package lv.javaguru.java2.cookingApp.consoleui;

import org.springframework.stereotype.Component;


@Component
public class ExitUIAction implements UIAction{
    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
