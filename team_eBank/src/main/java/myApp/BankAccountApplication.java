package myApp;

import myApp.consoleUI.*;

import java.util.Scanner;


class BankAccountApplication {

    private static UIActionMap uiActionMap = new UIActionMap();

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        logIn();
        while (true) {
            String personalCode = uiActionMap.getPersonalCode();
            if (personalCode == null || personalCode.isEmpty()) {
                System.out.println();
                System.out.println("User not found");
                System.out.println();
                logIn();
            } else {
                if (isUserAdmin()) {
                    ifAdminLogin(personalCode);
                } else {
                    ifUserLogin(personalCode);
                }
            }
        }
    }

    private static void ifAdminLogin(String personalCode) {
        printInformationForAdmin();
        int result = userChoice();
        userSelectionResult(result, personalCode);
    }

    private static void ifUserLogin(String personalCode) {
        printInformationForRegularUser();
        int result = userChoice();
        userSelectionResult(result, personalCode);
    }

    private static void printInformationForRegularUser() {
        System.out.println();
        System.out.println("Menu: ");
        System.out.println("1 - Transfer money");
        System.out.println("2 - Open an account");
        System.out.println("3 - Close an account");
        System.out.println("4 - See your accounts");
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
        uiActionMap.logIn();
    }

    private static boolean isUserAdmin() {
        return uiActionMap.isUserAdmin();
    }

    private static void userSelectionResult(int userChoice, String personalCode) {
        if (isUserAdmin()) {
            UIAction result = uiActionMap.userSelectionForAdmin(userChoice);
            result.execute();
        } else {
            UIAction result = uiActionMap.userSelectionForRegularUser(userChoice);
            result.execute();
        }
    }

    private static int userChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        return scanner.nextInt();
    }
}