package lv.javaguru.java2.repo_men_inc;

import lv.javaguru.java2.repo_men_inc.config.RepoMenIncConfiguration;
import lv.javaguru.java2.repo_men_inc.console_ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class RepoMenIncApplication {
    private static ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(RepoMenIncConfiguration.class);

    public static void main(String[] args) {

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
            case 1 -> {
                AddDebtorUIAction addNewDebtor = applicationContext.getBean(AddDebtorUIAction.class);
                addNewDebtor.execute();
            }
            case 2 -> {
                AddHarvestedItemUIAction addHarvestedItem = applicationContext.getBean(AddHarvestedItemUIAction.class);
                addHarvestedItem.execute();
            }
            case 3 -> {
                RemoveDebtorUIAction removeDebtor = applicationContext.getBean(RemoveDebtorUIAction.class);
                removeDebtor.execute();
            }
            case 4 -> {
                PrintDebtorListUIAction printDebtorList = applicationContext.getBean(PrintDebtorListUIAction.class);
                printDebtorList.execute();
            }
            case 5 -> {
                SearchDebtorUIAction searchDEbtorUIAction = applicationContext.getBean(SearchDebtorUIAction.class);
                searchDEbtorUIAction.execute();
            }
            case 6 -> {
                ExitUIAction exitUIAction = applicationContext.getBean(ExitUIAction.class);
                exitUIAction.execute();
            }
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
