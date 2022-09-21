package lv.javaguru.java2.repo_men_inc;

import lv.javaguru.java2.repo_men_inc.console_ui.*;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.database.DatabaseImpl;
import lv.javaguru.java2.repo_men_inc.services.AddDebtorService;
import lv.javaguru.java2.repo_men_inc.services.AddHarvestedItemService;
import lv.javaguru.java2.repo_men_inc.services.PrintDebtorListService;
import lv.javaguru.java2.repo_men_inc.services.RemoveDebtorService;

import java.util.Scanner;

public class RepoMenIncApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Database databaseImpl = new DatabaseImpl();

    private static final AddDebtorService addDebtorService = new AddDebtorService(databaseImpl);
    private static final AddDebtorUIAction addNewDebtor = new AddDebtorUIAction(addDebtorService, scanner);

    private static final RemoveDebtorService removeDebtorService = new RemoveDebtorService(databaseImpl);
    private static final RemoveDebtorUIAction removeDebtor = new RemoveDebtorUIAction(removeDebtorService, scanner);

    private static final PrintDebtorListService printDebtorListService = new PrintDebtorListService(databaseImpl);
    private static final PrintDebtorListUIAction printDebtorList = new PrintDebtorListUIAction(printDebtorListService);

    private static final AddHarvestedItemService addHarvestedItemService = new AddHarvestedItemService(databaseImpl);
    private static final AddHarvestedItemUIAction addHarvestedItem = new AddHarvestedItemUIAction(addHarvestedItemService, scanner);

    private static final ExitUIAction exitUIAction = new ExitUIAction();

    public static void main(String[] args) {

        while (true) {
            printMenu();
            int userChoice = getUserChoice();
            executeUsersChoice(userChoice);
        }
    }

    private static void executeUsersChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> addNewDebtor.execute();
            case 2 -> addHarvestedItem.execute();
            case 3 -> removeDebtor.execute();
            case 4 -> printDebtorList.execute();
            case 5 -> exitUIAction.execute();
        }
    }

    private static int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printMenu() {
        System.out.println("========================================================");
        System.out.println("Program menu:");
        System.out.println("1. Add new debtor to list");
        System.out.println("2. Add harvested item to debtors list");
        System.out.println("3. Delete debtor from list");
        System.out.println("4. Show all debtors in the list");
        System.out.println("5. Exit");
        System.out.println("========================================================");
    }
}