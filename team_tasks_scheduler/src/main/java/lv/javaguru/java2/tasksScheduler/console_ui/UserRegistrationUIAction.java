package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.services.UserRegistrationService;

import java.util.Scanner;

public class UserRegistrationUIAction implements UIAction {

    private UserRegistrationService userRegistrationService;

    public UserRegistrationUIAction(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter mobilePhone: ");
        String mobilePhone = scanner.nextLine();
        userRegistrationService.execute(username, password, email, mobilePhone);
        System.out.println("Registration successfully completed.");
        return true;
    }
}
