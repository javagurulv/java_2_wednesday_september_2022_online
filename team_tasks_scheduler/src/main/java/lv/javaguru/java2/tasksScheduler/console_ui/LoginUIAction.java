package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.services.GetAllUsersService;
import lv.javaguru.java2.tasksScheduler.services.LoginService;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.Scanner;

public class LoginUIAction implements UIAction {

    private LoginService loginService;

    public LoginUIAction(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        if (loginService.execute(username, password)) {
            System.out.println("Welcome to the system, " + username);
            return true;
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return false;
        }
    }
}
