package myApp;

import myApp.consoleUI.*;
import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.services.*;

import java.util.Scanner;

class BankAccountApplication {

    private static DataBase dataBase = new InMemoryDatabaseImpl();
    private static AddBankAccountValidator validator = new AddBankAccountValidator();
    private static AddBankAccountService service = new AddBankAccountService(dataBase, validator);
    private static UIAction addUIAction = new AddBankAccountUIAction(service);
    private static RemoveBankAccountValidator removeBankAccountValidator = new RemoveBankAccountValidator();
    private static RemoveBankAccountService removeBankAccountService = new RemoveBankAccountService(dataBase
            , removeBankAccountValidator);
    private static UIAction removeUIAction = new RemoveBankAccountUIAction(removeBankAccountService);
    private static UIAction getAllAccountsUIAction = new GetAllAccountsUIAction(dataBase);
    private static MoneyTransferValidator moneyTransferValidator = new MoneyTransferValidator();
    private static MoneyTransferService moneyTransferService = new MoneyTransferService(dataBase
            , moneyTransferValidator);
    private static UIAction moneyTransfer = new MoneyTransferUIAction(moneyTransferService);
    private static UIAction exit = new ExitUIAction();

    public static void main(String[] args) {
        while (true) {
            printInformation();
            int result = userChoice();
            userSelectionResult(result);
        }
    }

    private static void printInformation() {
        System.out.println();
        System.out.println("Menu: ");
        System.out.println("1 - Get all accounts");
        System.out.println("2 - Add account");
        System.out.println("3 - Remove account");
        System.out.println("4 - Transfer money");
        System.out.println("5 - Exit");

    }

    private static void userSelectionResult(int userChoice) {
        switch (userChoice) {
            case 1 -> getAllAccountsUIAction.execute();
            case 2 -> addUIAction.execute();
            case 3 -> removeUIAction.execute();
            case 4 -> moneyTransfer.execute();
            case 5 -> exit.execute();
        }
    }

    private static int userChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        return scanner.nextInt();
    }
}