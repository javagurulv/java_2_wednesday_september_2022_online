package myApp;

import myApp.consoleUI.*;
import myApp.core.services.UserAreAdminService;
import myApp.core.services.UserService;
import myApp.dependency_injection.ApplicationContext;
import myApp.dependency_injection.DIApplicationContextBuilder;

import java.util.Scanner;


class BankAccountApplication {

    private static ApplicationContext applicationContext = new DIApplicationContextBuilder().build("myApp");

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        logIn();
        while (true) {
            String personalCode = getPersonalCode();
            if (personalCode == null || personalCode.isEmpty()) {
                System.out.println();
                System.out.println("User not found");
                System.out.println();
                logIn();
            } else {
                if (isUserAdmin()) {
                    ifAdminLogin();
                } else {
                    ifUserLogin();
                }
            }
        }
    }

    private static void ifAdminLogin() {
        printInformationForAdmin();
        int result = userChoice();
        userSelectionResult(result);
    }

    private static void ifUserLogin() {
        printInformationForRegularUser();
        int result = userChoice();
        userSelectionResult(result);
    }

    private static void printInformationForRegularUser() {
        System.out.println();
        System.out.println("Menu: ");
        System.out.println("1 - Transfer money");
        System.out.println("2 - Open an account");
        System.out.println("3 - Close an account");
        System.out.println("4 - See your account");
       // System.out.println("Take a loan");//need to add
        System.out.println("5 - Switch user");
        System.out.println("6 - Exit");
    }

    private static void printInformationForAdmin() {
        System.out.println();
        System.out.println("Admin menu: ");
        System.out.println("1 - Get all bank accounts");
        System.out.println("2 - Add bank account");
        System.out.println("3 - Remove bank account");
        System.out.println("4 - Search bank account");
        System.out.println("5 - Switch user");
        System.out.println("6 - Exit");
    }

    private static void logIn() {
        LogInUIAction uiAction = applicationContext.getBean(LogInUIAction.class);
        uiAction.execute();
    }

    private static boolean isUserAdmin() {
        UserService userService = applicationContext.getBean(UserService.class);
        UserAreAdminService userAreAdminService = applicationContext.getBean(UserAreAdminService.class);
        return userAreAdminService.isUserAreAdmin(userService.getPersonalCode());
    }

    private static void userSelectionResult(int userChoice) {
        if (isUserAdmin()) {
            switch (userChoice) {
                case 1 -> {
                    GetAllAccountsUIAction uiAction = applicationContext.getBean(GetAllAccountsUIAction.class);
                    uiAction.execute();
                }
                case 2 -> {
                    AddBankAccountUIAction uiAction = applicationContext.getBean(AddBankAccountUIAction.class);
                    uiAction.execute();
                }
                case 3 -> {
                    RemoveBankAccountUIAction uiAction = applicationContext.getBean(RemoveBankAccountUIAction.class);
                    uiAction.execute();
                }
                case 4 -> {
                    SearchBankAccountUIAction uiAction = applicationContext.getBean(SearchBankAccountUIAction.class);
                    uiAction.execute();
                }
                case 5 -> {
                    SwitchUserUIAction uiAction = applicationContext.getBean(SwitchUserUIAction.class);
                    uiAction.execute();
                }
                case 6 -> {
                    ExitUIAction uiAction = applicationContext.getBean(ExitUIAction.class);
                    uiAction.execute();
                }
            }
        } else {
            switch (userChoice) {
                case 1 -> {
                    MoneyTransferUIAction uiAction = applicationContext.getBean(MoneyTransferUIAction.class);
                    uiAction.execute();
                }
                case 2 -> {
                    OpenAccountUIAction uiAction = applicationContext.getBean(OpenAccountUIAction.class);
                    uiAction.execute();
                }
                case 3 -> {
                    CloseAccountUIAction uiAction = applicationContext.getBean(CloseAccountUIAction.class);
                    uiAction.execute();
                }
                case 4 -> {
                    SeeYourAccountUIAction uiAction = applicationContext.getBean(SeeYourAccountUIAction.class);
                    uiAction.execute();
                }
                case 5 -> {
                    SwitchUserUIAction uiAction = applicationContext.getBean(SwitchUserUIAction.class);
                    uiAction.execute();
                }
                case 6 -> {
                    ExitUIAction uiAction = applicationContext.getBean(ExitUIAction.class);
                    uiAction.execute();
                }
            }
        }
    }

    private static int userChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        return scanner.nextInt();
    }
    private static String getPersonalCode() {
        UserService userService = applicationContext.getBean(UserService.class);
        return userService.getPersonalCode();
    }
}