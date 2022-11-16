package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.dependency_injection.DIComponent;
import org.springframework.stereotype.Component;

@Component
public class ExitApplicationUIAction implements UIAction {

    public void execute() {
        System.out.println("Application closed");
        System.exit(0);
    }
}
