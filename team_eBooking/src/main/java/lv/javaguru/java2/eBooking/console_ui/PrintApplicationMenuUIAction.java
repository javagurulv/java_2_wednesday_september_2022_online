package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.dependency_injection.DIComponent;
import org.springframework.stereotype.Component;

@Component
public class PrintApplicationMenuUIAction implements UIAction {
    public void execute() {
        System.out.println("Appointment Application");
        System.out.println("");
        System.out.println("1. Add a client");
        System.out.println("2. Delete client");
        System.out.println("3. Show client list");
        System.out.println("4. Client search");
        System.out.println("5. Add appointment");
        System.out.println("6. Delete appointment");
        System.out.println("7. Show all appointments");
        System.out.println("8. Appointment search");
        System.out.println("9. Exit");
    }
}
