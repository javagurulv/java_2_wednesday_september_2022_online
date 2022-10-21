package myApp.consoleUI;

import myApp.core.requests.LogInRequest;
import myApp.core.responses.LogInResponse;
import myApp.core.services.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

@Component
public class LogInUIAction implements UIAction {
    @Autowired
    private LogInService logInService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter personal code: ");
        String personalCode = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        String encodePassword = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
        LogInRequest request = new LogInRequest(personalCode, encodePassword);
        LogInResponse response = logInService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().stream()
                    .map(coreError -> "Field: " + coreError.getField() + "\n Message: " + coreError.getMessage()).forEach(System.out::println);
            System.out.println();
        }
    }
}
