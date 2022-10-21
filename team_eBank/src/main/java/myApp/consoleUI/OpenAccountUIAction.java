package myApp.consoleUI;

import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.services.OpenAccountService;
import myApp.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class OpenAccountUIAction implements UIAction {
    @Autowired
    private OpenAccountService service;
    @Autowired
    private UserService userService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String personalCode = userService.getPersonalCode();
        System.out.println("Enter amount: ");
        Integer value = scanner.nextInt();
        OpenAccountRequest request = new OpenAccountRequest(personalCode,value);
        OpenAccountResponse response = service.execute(request);
        if (response.isCompleted()) {
            System.out.println("Account has added");
        } else {
            System.out.println("Error");;
        }
    }
}