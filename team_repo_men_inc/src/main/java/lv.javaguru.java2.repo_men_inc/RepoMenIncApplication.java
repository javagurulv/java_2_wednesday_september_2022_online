package lv.javaguru.java2.repo_men_inc;

import java.util.Scanner;

public class RepoMenIncApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Database databaseImpl = new DatabaseImpl();

    public static void main(String[] args) {

        while (true) {
            printMenu();
            int userChoice = getUserChoice();
            executeUsersChoice(userChoice);
        }
    }

    private static void executeUsersChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> addNewDebtor();
            case 2 -> addHarvestedItem();
            case 3 -> removeDebtor();
            case 4 -> printDebtorList();
            case 5 -> exitProgram();
        }
    }

    private static void exitProgram() {
        System.out.println("Good by!");
        System.exit(0);
    }

    private static void printDebtorList() {
        System.out.println("Debtors list: ");
        databaseImpl.getAllDebtors().forEach(System.out::println);
        System.out.println("Debtor list end.");
    }

    private static void removeDebtor() {
        System.out.println("Enter debtors Id: ");
        Long debtorsId = Long.parseLong(scanner.nextLine());
        databaseImpl.delete(debtorsId);
        System.out.println("Debtor was removed from list.");
    }

    private static void addHarvestedItem() {
        System.out.println("Enter debtors Id: ");
        Long debtorsId = Long.parseLong(scanner.nextLine());
        System.out.println("Enter harvested item");
        String harvestedItem = scanner.nextLine();
        databaseImpl.getById(debtorsId).addIem(harvestedItem);
        System.out.println("Harvested Item added to debtors list.");
    }

    private static void addNewDebtor() {
        System.out.println("Enter debtors name: ");
        String debtorsName = scanner.nextLine();
        Debtor debtor = new Debtor(debtorsName);
        databaseImpl.save(debtor);
        System.out.println("New Debtor was added to list.");
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
