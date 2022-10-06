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
        System.out.println("4 - Close an account");
        System.out.println("5 - Take a loan");
        System.out.println("6 - Switch user");
        System.out.println("7 - Exit");
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

    private static void userSelectionResult(int userChoice) {
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