package lv.javaguru.java2.tasksScheduler;

import lv.javaguru.java2.tasksScheduler.console_ui.*;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;

import java.util.Scanner;

public class TasksSchedulerApplication {

    private static UIActionMap uiActionMap = new UIActionMap();

    private static MenuType menuType = MenuType.START;

    public static void main(String[] args) {
        int menuNumber = 0;

        while (true) {
            printMenu(menuType);
            menuNumber = getMenuNumberFromUser();
            executeSelectedMenuItem(menuType, menuNumber);
        }
    }

    private static void printMenu(MenuType type) {
        switch (type) {
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
        System.out.println("1. Show all users registered in the system");
        System.out.println("2. Login");
        System.out.println("3. User registration");
        System.out.println("4. Exit");
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

    private static int getMenuNumberFromUser() {
        try {
            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            return Integer.parseInt(scanner.nextLine());
        } catch (RuntimeException e) {
            return 0;
        }
    }

    private static void executeSelectedMenuItem(MenuType type, int choice) {

        UIAction selectedAction = uiActionMap.getAction(getAlignedMenuChoice(type, choice));
        if (selectedAction == null) {
            System.out.println("Invalid menu option selected. Please try again.");
            return;
        }
        if (selectedAction.execute()) {
            toggleMenuType(getAlignedMenuChoice(type, choice));
        }
    }

    private static int getAlignedMenuChoice(MenuType menuType, int choice) {
        if (choice == 0)
            return 0;
        if (menuType == MenuType.USER)
            return (choice + 4);
        return choice;
    }

    private static void toggleMenuType(int selectedMenuOption) {
        switch (selectedMenuOption) {
            case 0:
                return;
            case 2:
                menuType = MenuType.USER;
                return;
            case 12, 13:
                menuType = MenuType.START;
                return;
            default:
                break;
        }
    }
}