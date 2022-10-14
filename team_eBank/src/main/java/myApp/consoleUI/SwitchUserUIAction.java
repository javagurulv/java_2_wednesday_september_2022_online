package myApp.consoleUI;

import myApp.core.requests.SwitchUserRequest;
import myApp.core.responses.SwitchUserResponse;
import myApp.core.services.SwitchUserService;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.Scanner;


@DIComponent
public class SwitchUserUIAction implements UIAction {
    @DIDependency
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
