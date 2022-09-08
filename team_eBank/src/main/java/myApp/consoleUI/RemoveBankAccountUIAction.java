package myApp.consoleUI;

import myApp.services.RemoveBankAccountService;
import myApp.database.DataBase;

import java.util.Scanner;

public class RemoveBankAccountUIAction implements UIAction {

    private RemoveBankAccountService service;
    public RemoveBankAccountUIAction(DataBase dataBase) {
        service = new RemoveBankAccountService(dataBase);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account id: ");
        Long id = scanner.nextLong();
        service.execute(id);
        System.out.println("Bank account has been deleted");
    }
}
