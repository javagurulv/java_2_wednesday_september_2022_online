package lv.javaguru.java2.tasksScheduler.console_ui;



import lv.javaguru.java2.tasksScheduler.core.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserRegistrationUIAction implements UIAction {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Override
    public boolean execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Push 'Y' if reminders by email are required: ");
        boolean sendReminders = scanner.nextLine().equalsIgnoreCase("Y");

        UserRegistrationRequest request = new UserRegistrationRequest(username, password, email, sendReminders);
        UserRegistrationResponse response = userRegistrationService.execute(request);

        if (response.hasErrors()) {
            System.out.println("Registration failed");
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        }
        else {
            System.out.println("Registration successfully completed. User ID = " + response.getUser().getId());
            return true;
        }
    }
}
