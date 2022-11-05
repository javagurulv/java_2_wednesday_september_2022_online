package lv.javaguru.java2.tasksScheduler;

import lv.javaguru.java2.tasksScheduler.config.TaskSchedulerConfig;
import lv.javaguru.java2.tasksScheduler.console_ui.*;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.services.scheduled_jobs.TasksCleanupService;
import lv.javaguru.java2.tasksScheduler.utils.TestData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.Scanner;

public class TasksSchedulerApplication {
    private static  MenuType menuType = MenuType.START;


    public static void main(String[] args) {

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);

        UIActionMap uiActionMap = applicationContext.getBean(UIActionMap.class);
        TasksCleanupService cleanupService = applicationContext.getBean(TasksCleanupService.class);

        TestData testData = applicationContext.getBean(TestData.class); //TODO remove me
        testData.createTestSettings();
        testData.createTestUsers();
        testData.createTestTasks();

        int menuNumber = 0;

        do {
            // Loop to set up Settings on the first run
            if (executeSelectedMenuItem(uiActionMap, menuType, menuNumber))
                break;
        } while (true);

        //start 2nd thread
//        cleanupService.start();

        while (true) {
            printMenu(menuType);
            menuNumber = getMenuNumberFromUser();
            executeSelectedMenuItem(uiActionMap, menuType, menuNumber);
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
        System.out.println("1. Show usernames registered in the system");
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
        System.out.println("5. Search tasks");
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
        System.out.println("1. Show users information");
        System.out.println("2. Amend settings");
        System.out.println("3. Run jobs manually");
        System.out.println("4. Exit settings");
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

    private static boolean executeSelectedMenuItem(UIActionMap uiActionMap,
                                                    MenuType type, int choice) {

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