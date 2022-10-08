package lv.javaguru.java2.tasksScheduler;

import lv.javaguru.java2.tasksScheduler.console_ui.*;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;

import java.util.Scanner;

public class TasksSchedulerApplication {

    private static UIActionMap uiActionMap = new UIActionMap();

    private static MenuType menuType = MenuType.START;

    public static void main(String[] args) {
        int menuNumber = 0;

        do {
            // Loop to set up Settings on the first run
            if (executeSelectedMenuItem(menuType, menuNumber))
                break;
        } while (true);

        while (true) {
            printMenu(menuType);
            menuNumber = getMenuNumberFromUser();
            executeSelectedMenuItem(menuType, menuNumber);
        }
    }

    private static void printMenu(MenuType type) {
        switch (type) {
            case ADMIN:
                printAdminMenu();
                break;
            case START:
                printStartMenu();
                break;
            case USER:
                printUserMenu();
                break;
            default:
                break;
        }
    }

    private static void printStartMenu() {
        System.out.println();
        System.out.println("********************");
        System.out.println("1. Show all usernames registered in the system");
        System.out.println("2. User Login");
        System.out.println("3. User registration");
        System.out.println("4. System settings");
        System.out.println("5. Exit");
        System.out.println("********************");
        System.out.println();
    }

    private static void printUserMenu() {
        System.out.println();
        System.out.println("********************");
        System.out.println("1. Show outstanding tasks");
        System.out.println("2. Show tasks for today");
        System.out.println("3. Add task");
        System.out.println("4. Amend task");
        System.out.println("5. Search task");
        System.out.println("6. Delete tasks");
        System.out.println("7. Amend current user information");
        System.out.println("8. Delete current user");
        System.out.println("9. Logout");
        System.out.println("********************");
        System.out.println();
    }

    private static void printAdminMenu() {
        System.out.println();
        System.out.println("********************");
        System.out.println("1. Show all users information");
        System.out.println("2. Change administrator password");
        System.out.println("3. Amend settings");
        System.out.println("4. Exit menu");
        System.out.println("********************");
        System.out.println();
    }

    private static int getMenuNumberFromUser() {
        try {
            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            return Integer.parseInt(scanner.nextLine());
        } catch (RuntimeException e) {
            return 0;
        }
    }

    private static boolean executeSelectedMenuItem(MenuType type, int choice) {

        UIAction selectedAction = uiActionMap.getAction(getAlignedMenuChoice(type, choice));
        if (selectedAction == null) {
            System.out.println("Invalid menu option selected. Please try again.");
            return false;
        }
        if (selectedAction.execute()) {
            toggleMenuType(getAlignedMenuChoice(type, choice));
            return true;
        }
        return false;
    }

    private static int getAlignedMenuChoice(MenuType menuType, int choice) {
        if (choice == 0)
            return 0;
        if (menuType == MenuType.USER)
            return (choice + 5);
        if (menuType == MenuType.ADMIN)
            return (choice + 14);
        return choice;
    }

    private static void toggleMenuType(int selectedMenuOption) {
        switch (selectedMenuOption) {
            case 2:
                menuType = MenuType.USER;
                break;
            case 4:
                menuType = MenuType.ADMIN;
                break;
            case 13, 14, 18:
                menuType = MenuType.START;
                break;
            default:
                break;
        }
    }
}