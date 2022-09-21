package myApp.consoleUI;

import myApp.core.requests.AddAccountRequest;
import myApp.core.responses.AddAccountResponse;
import myApp.core.services.AddAccountService;

import java.util.Scanner;

public class AddAccountUIAction implements UIAction {

    private AddAccountService service;

    public AddAccountUIAction(AddAccountService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter personal code: ");
        String name = scanner.nextLine();
        AddAccountRequest request = new AddAccountRequest(name);
        AddAccountResponse response = service.execute(request);
        System.out.println("Account has added");
    }
}
