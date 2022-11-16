package myApp.consoleUI;

import myApp.core.requests.LogInRequest;
import myApp.core.responses.LogInResponse;
import myApp.core.services.authentication.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LogInUIAction implements UIAction {
    @Autowired
    private LogInService logInService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login: ");
        String personalCode = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        LogInRequest request = new LogInRequest(personalCode, password);
        LogInResponse response = logInService.execute(request);
        if (response.hasErrors() &&  response.getPersonalCode() == null) {
            response.getErrors().stream()
                    .map(coreError -> "Field: " + coreError.getField() + "\n Message: " + coreError.getMessage()).forEach(System.out::println);
            System.out.println();

        } else {
            System.out.println("Welcome!");
        }
    }
}
