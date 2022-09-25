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
        System.out.println("Enter mobile phone: ");
        String mobilePhone = scanner.nextLine();
        boolean result = userRegistrationService.execute(username, password, email, mobilePhone);
        if (result)
            System.out.println("Registration successfully completed.");
        else
            System.out.println("Registration failed");
        return result;
    }
}
