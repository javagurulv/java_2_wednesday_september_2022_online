package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.UserRegistrationService;

import java.util.Scanner;

@DIComponent
public class UserRegistrationUIAction implements UIAction {

    @DIDependency private UserRegistrationService userRegistrationService;

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
        boolean sendReminders = scanner.nextLine().equals("Y");

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
            System.out.println("Registration successfully completed. User ID:" + response.getUser().getId());
            return true;
        }
    }
}
