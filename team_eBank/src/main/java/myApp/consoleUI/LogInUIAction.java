package myApp.consoleUI;

import myApp.core.requests.LogInRequest;
import myApp.core.responses.LogInResponse;
import myApp.core.services.LogInService;

import java.util.Scanner;

public class LogInUIAction implements UIAction {

    private LogInService logInService;

    public LogInUIAction(LogInService logInService) {
        this.logInService = logInService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter personal code: ");
        String personalCode = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        LogInRequest request = new LogInRequest(personalCode, password);
        LogInResponse response = logInService.execute(request);
    }
}
