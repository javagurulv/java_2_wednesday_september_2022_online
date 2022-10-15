package myApp.consoleUI;

import myApp.core.requests.SwitchUserRequest;
import myApp.core.services.SwitchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class SwitchUserUIAction implements UIAction {

    @Autowired
    private SwitchUserService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your personal code: ");
        String personalCode = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        SwitchUserRequest request = new SwitchUserRequest(personalCode, password);
        String response = service.execute(request);
    }
}
