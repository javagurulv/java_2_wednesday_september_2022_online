package myApp.consoleUI;

import myApp.core.requests.LogInRequest;
import myApp.core.responses.LogInResponse;
import myApp.core.services.LogInService;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class LogInUIAction implements UIAction {
    @DIDependency
    private LogInService logInService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter personal code: ");
        String personalCode = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        LogInRequest request = new LogInRequest(personalCode, password);
        LogInResponse response = logInService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().stream()
                    .map(coreError -> "Field: " + coreError.getField() + "\n Message: " + coreError.getMessage()).forEach(System.out::println);
            System.out.println();
        }
    }
}
