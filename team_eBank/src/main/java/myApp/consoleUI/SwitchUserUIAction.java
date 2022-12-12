package myApp.consoleUI;

import myApp.core.requests.SwitchUserRequest;
import myApp.core.responses.SwitchUserResponse;
import myApp.core.services.authentication.SwitchUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;


//@Component
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
        SwitchUserResponse response = service.execute(request);
        if (response.hasErrors()) {
            if (response.hasErrors()) {
                response.getErrors().forEach(coreError -> System.out.println("Error: "
                        + coreError.getField() + " " + coreError.getMessage()));
            } else {
                System.out.println("Welcome!");
            }
        }
    }
}
