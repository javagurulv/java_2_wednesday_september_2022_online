package lv.javaguru.java2.repo_men_inc;

import lv.javaguru.java2.repo_men_inc.console_ui.*;
import lv.javaguru.java2.repo_men_inc.core.validators.AddDebtorValidator;
import lv.javaguru.java2.repo_men_inc.core.validators.AddHarvestedItemValidator;
import lv.javaguru.java2.repo_men_inc.core.validators.RemoveDebtorValidator;
import lv.javaguru.java2.repo_men_inc.core.validators.SearchDebtorValidator;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.database.DatabaseImpl;
import lv.javaguru.java2.repo_men_inc.domain.Debtor;
import lv.javaguru.java2.repo_men_inc.services.*;

import java.util.Scanner;

public class RepoMenIncApplication {

    private static final Database databaseImpl = new DatabaseImpl();

    private static final AddDebtorValidator addDebtorValidator = new AddDebtorValidator(databaseImpl);
    private static final AddDebtorService addDebtorService = new AddDebtorService(databaseImpl, addDebtorValidator);
    private static final AddDebtorUIAction addNewDebtor = new AddDebtorUIAction(addDebtorService);

    private static final RemoveDebtorValidator removeDebtorValidator = new RemoveDebtorValidator(databaseImpl);
    private static final RemoveDebtorService removeDebtorService = new RemoveDebtorService(databaseImpl, removeDebtorValidator);
    private static final RemoveDebtorUIAction removeDebtor = new RemoveDebtorUIAction(removeDebtorService);

    private static final PrintDebtorListService printDebtorListService = new PrintDebtorListService(databaseImpl);
    private static final PrintDebtorListUIAction printDebtorList = new PrintDebtorListUIAction(printDebtorListService);

    private static final AddHarvestedItemValidator addHarvestedItemValidator = new AddHarvestedItemValidator(databaseImpl);
    private static final AddHarvestedItemService addHarvestedItemService = new AddHarvestedItemService(databaseImpl, addHarvestedItemValidator);
    private static final AddHarvestedItemUIAction addHarvestedItem = new AddHarvestedItemUIAction(addHarvestedItemService);

    private static final SearchDebtorValidator searchDebtorValidator = new SearchDebtorValidator();
    private static final SearchDebtorService searchDebtorService = new SearchDebtorService(databaseImpl, searchDebtorValidator);
    private static final SearchDebtorUIAction searchDEbtorUIAction = new SearchDebtorUIAction(searchDebtorService);

    private static final ExitUIAction exitUIAction = new ExitUIAction();

    public static void main(String[] args) {

        databaseImpl.save(new Debtor("mr x"));
        databaseImpl.save(new Debtor("mr y"));
        databaseImpl.save(new Debtor("mr z"));
        databaseImpl.getById(1L).getList().add("leg");
        databaseImpl.getById(2L).getList().add("arm");
        databaseImpl.getById(3L).getList().add("arm");

        while (true) {
            try {
                printMenu();
                int userChoice = getUserChoice();
                executeUsersChoice(userChoice);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("INVALID INPUT!");
            }
        }
    }

    private static void executeUsersChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> addNewDebtor.execute();
            case 2 -> addHarvestedItem.execute();
            case 3 -> removeDebtor.execute();
            case 4 -> printDebtorList.execute();
            case 5 -> searchDEbtorUIAction.execute();
            case 6 -> exitUIAction.execute();
        }
    }

    private static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("5. Search Debtors");
        System.out.println("6. Exit");
        System.out.println("========================================================");
    }
}
