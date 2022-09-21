package lv.javaguru.java2.tasksScheduler;

import lv.javaguru.java2.tasksScheduler.console_ui.ExitUIAction;
import lv.javaguru.java2.tasksScheduler.console_ui.GetAllUsersUIAction;
import lv.javaguru.java2.tasksScheduler.console_ui.UIAction;
import lv.javaguru.java2.tasksScheduler.console_ui.UserRegistrationUIAction;
import lv.javaguru.java2.tasksScheduler.database.InMemoryUsersRepositoryImpl;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.services.GetAllUsersService;
import lv.javaguru.java2.tasksScheduler.services.UserRegistrationService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;



public class TasksSchedulerApplication {

    private static UsersRepository usersRepository = new InMemoryUsersRepositoryImpl();

    private static GetAllUsersService getAllUsersService = new GetAllUsersService(usersRepository);
    private static UserRegistrationService userRegistrationService = new UserRegistrationService(usersRepository);

    private static UIAction getAllUsersUIAction = new GetAllUsersUIAction(getAllUsersService);
    private static UIAction userRegistrationUIAction = new UserRegistrationUIAction(userRegistrationService);
    private static UIAction exitUIAction = new ExitUIAction();

    private enum MenuType {START, USER};

    private static MenuType menuType = MenuType.START;

//    static void printStartMenu() {
//        System.out.println("1. Login");
//        System.out.println("2. User management");
//        System.out.println("3. Exit");
//        System.out.println("*******************");
//        System.out.println("");
//        System.out.println("Enter menu item number to execute:");
//    }
//
//    static void printTaskMenu() {
//        System.out.println("1. Print existing tasks");
//        System.out.println("2. Create task");
//        System.out.println("3. Delete task");
//        System.out.println("4. Exit");
//        System.out.println("************************");
//        System.out.println("");
//        System.out.println("Enter menu item number to execute:");
//    }
//
//    static void printUserManagementMenu() {
//        System.out.println("1. Add user");
//        System.out.println("2. Delete user");
//        System.out.println("3. Show all users");
//        System.out.println("4. Exit");
//        System.out.println("*******************");
//        System.out.println("");
//        System.out.println("Enter menu item number to execute:");
//    }
//
//    static int removeUserByName(ArrayList<User> users, String name) {
//        int idx = 0, result = 0;
//        for (User usr : users) {
//            if (usr.getUsername().equals(name)) {
//                result = 1;
//                break;
//            }
//            idx++;
//        }
//        if (result == 1) {
//            users.remove(idx);
//        }
//
//        return result;
//    }
//
//    static int removeTaskById(ArrayList<Task> tasks, long id) {
//        int idx = 0, result = 0;
//        for (Task tsk : tasks) {
//            if (tsk.getId() == id) {
//                result = 1;
//                break;
//            }
//            idx++;
//        }
//        if (result == 1) {
//            tasks.remove(idx);
//        }
//
//        return result;
//    }
//
//    static boolean isValidUser(ArrayList<User> users, String name, String pwd) {
//        boolean result = false;
//        for (User usr : users) {
//            if (usr.getUsername().equals(name)) {
//                if (usr.getPassword().equals(pwd)) {
//                    result = true;
//                }
//            }
//        }
//        return result;
//    }

    public static void main(String[] args) {

        int menuNumber = 0;

        while (true) {
            printMenu(menuType);
            menuNumber = getMenuNumberFromUser();
            executeSelectedMenuItem(menuType, menuNumber);
        }

//        Scanner scanner = new Scanner(System.in);
//
//
//        ArrayList<Task> tasks = new ArrayList<Task>();
//        ArrayList<User> users = new ArrayList<User>();
//
//        //add user for testing
//        User userTmp = new User("jb", "qwerty", "aaa@inbox.com", "12345678");
//        users.add(userTmp);
//        //add task for testing
//        Task taskTmp = new Task("testing", 1,
//                LocalDateTime.of(2022, 10, 15, 16, 40),
//                LocalDateTime.of(2022, 10, 16, 15, 30),
//                0L);
//        tasks.add(taskTmp);
//
//        while (true) {
//            printStartMenu();
//            int userChoice = Integer.parseInt(scanner.nextLine());
//
//            switch (userChoice) {
//
//                case 1: {
//                    System.out.println("Enter user name:");
//                    String userName = scanner.nextLine();
//                    System.out.println("Enter password:");
//                    String pwd = scanner.nextLine();
//                    if (isValidUser(users, userName, pwd) == false) {
//                        System.out.println("Incorrect name or/and password");
//                        break;
//                    }
//
//                    printTaskMenu();
//                    userChoice = Integer.parseInt(scanner.nextLine());
//                    switch (userChoice) {
//                        case 1: {
//                            System.out.println("All tasks:");
//                            for (Task tsk : tasks) {
//                                System.out.println(tsk);
//                            }
//                            System.out.println("********************");
//                            break;
//                        }
//
//                        case 2: {
//                            System.out.println("Enter task description");
//                            String tskDesc = scanner.nextLine();
//                            System.out.println("Enter task regularity");
//                            int tskRegular = Integer.parseInt(scanner.nextLine());
//                            System.out.println("Enter task due date (yyyy-MM-dd HH:mm)");
//                            String tskDueDateStr = scanner.nextLine();
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//                            LocalDateTime dateTimeDue = LocalDateTime.parse(tskDueDateStr, formatter);
//                            System.out.println("Enter task end date (yyyy-MM-dd HH:mm)");
//                            String tskEndDateStr = scanner.nextLine();
//                            LocalDateTime dateTimeEnd = LocalDateTime.parse(tskEndDateStr, formatter);
//                            Task task = new Task(tskDesc, tskRegular, dateTimeDue, dateTimeEnd, 0L);
//                            tasks.add(task);
//                            break;
//                        }
//                        case 3: {
//                            System.out.println("Enter task ID");
//                            long taskId = Long.parseLong(scanner.nextLine());
//                            if (removeTaskById(tasks, taskId) == 1) {
//                                System.out.println("Task removed");
//                            }
//                            else {
//                                System.out.println("Task not found");
//                            }
//                            break;
//                        }
//                        default: {
//                            System.out.println("Incorrect menu item");
//                            break;
//                        }
//                    }
//                    break;
//                }
//
//
//                case 2: {
//                    printUserManagementMenu();
//                    userChoice = Integer.parseInt(scanner.nextLine());
//                    switch (userChoice) {
//                        case 1: {
//                            System.out.println("Enter user name:");
//                            String userName = scanner.nextLine();
//                            System.out.println("Enter user password:");
//                            String userPwd = scanner.nextLine();
//                            System.out.println("Enter user e-mail:");
//                            String userEMail = scanner.nextLine();
//                            System.out.println("Enter user phone number:");
//                            String userPhonenumber = scanner.nextLine();
//                            User newUser = new User(userName, userPwd, userEMail, userPhonenumber);
//                            System.out.println("User added");
//                            users.add(newUser);
//                            break;
//                        }
//                        case 2: {
//                            System.out.println("Enter user name:");
//                            String userName = scanner.nextLine();
//                            if (removeUserByName(users, userName) == 1) {
//                                System.out.println("User deleted");
//                            }
//                            else {
//                                System.out.println("Username not found");
//                            }
//                            break;
//                        }
//                        case 3: {
//                            System.out.println("All user:");
//                            for (User usr : users) {
//                                System.out.println(usr);
//                            }
//                            break;
//                        }
//
//                    }
//                    break;
//                }
//
//
//                case 3: {
//                    System.out.println("Good by");
//                    System.exit(0);
//                    break;
//                }
//
//                default:
//                    System.out.println("Incorrect menu item.");
//                    break;
//
//            }
//        }
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
        System.out.println("1. Show all users");
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
        System.out.println("5. Delete task");
        System.out.println("6. Delete user");
        System.out.println("7. Logout");
        System.out.println("********************");
        System.out.println();
    }

    private static int getMenuNumberFromUser() {
        try {
            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            return Integer.parseInt(scanner.nextLine());
        }
        catch (RuntimeException e) {
            return 0;
        }
    }
    private static void executeSelectedMenuItem(MenuType type, int choice) {
        switch (type) {
            case START:
                executeSelectedStartMenuItem(choice);
                break;
            case USER:
                executeSelectedUserMenuItem(choice);
                break;
            default:
                break;
        }
    }

    private static void executeSelectedStartMenuItem(int selectedMenu) {
        switch (selectedMenu) {
            case 1: {
                getAllUsersUIAction.execute();
                break;
            }
            case 2: {
                //TODO: check credentials
                //TODO: store user id on successful login
                menuType = MenuType.USER;
                break;
            }
            case 3: {
                userRegistrationUIAction.execute();
                break;
            }
            case 4: {
                exitUIAction.execute();
                break;
            }
            default:
                System.out.println("Invalid menu option selected. Please try again.");
        }
    }

    private static void executeSelectedUserMenuItem(int selectedMenu) {
        switch (selectedMenu) {
//            case 1: {
//                addBookUIAction.execute();
//                break;
//            }
//            case 2: {
//                removeBookUIAction.execute();
//                break;
//            }
//            case 3: {
//                exitUIAction.execute();
//                break;
//            }
//            case 4: {
//                addBookUIAction.execute();
//                break;
//            }
//            case 5: {
//                removeBookUIAction.execute();
//                break;
//            }
//            case 6: {
//                exitUIAction.execute();
                //TODO: delete all tasks for deleted user
//                break;
//            }
            case 7: {
                //TODO: forget user id
                menuType = MenuType.START;
                break;
            }
            default:
                System.out.println("Invalid menu option selected. Please try again.");
        }
    }
}