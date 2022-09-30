package myApp.consoleUI;

import myApp.core.services.SwitchUserService;

import java.util.Scanner;


public class SwitchUserUIAction implements UIAction {

    private SwitchUserService service;

    public SwitchUserUIAction(SwitchUserService service) {
        this.service = service;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your personal code: ");
        String personalCode = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        service.execute(personalCode, password);
    }
}
