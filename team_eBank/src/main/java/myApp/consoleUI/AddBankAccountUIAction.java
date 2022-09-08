package myApp.consoleUI;

import myApp.services.AddBankAccountService;
import myApp.database.DataBase;

import java.util.Scanner;

public class AddBankAccountUIAction implements UIAction {

    private AddBankAccountService service;

    public AddBankAccountUIAction(DataBase dataBase) {
        service = new AddBankAccountService(dataBase);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter balance: ");
        int balance = scanner.nextInt();
        service.execute(name, surname, balance);
        System.out.println("Account has been added");
    }
}
