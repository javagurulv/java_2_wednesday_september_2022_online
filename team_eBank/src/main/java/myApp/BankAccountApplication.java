package myApp;
import myApp.consoleUI.*;

import java.util.Scanner;


class BankAccountApplication {

    private static UIActionMap uiActionMap = new UIActionMap();

    public static void main(String[] args) {
          String personalCode = logIn();
        while (true) {
            if (personalCode != null) {
                if (isUserAdmin(personalCode)) {
                    ifAdminLogin();
                } else {
                    ifUserLogin();
                    switchUser(userChoice(), 5);//need to finalize
                }
            }
        }
    }

    private static void ifAdminLogin() {
        printInformationForAdmin();
        int result = userChoice();
        adminUserSelectionResult(result);
    }

    private static void ifUserLogin() {
        printInformationForRegularUser();
        int result = userChoice();
        regularUserSelectionResult(result);
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
        System.out.println("4 - Switch user");
        System.out.println("5 - Exit");
    }

    private static String logIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your personal code: ");
        String personalCode = scanner.nextLine();
        return uiActionMap.logIn(personalCode);
    }

    private static boolean isUserAdmin(String personalCode) {
        return uiActionMap.isUserAdmin(personalCode);
    }

    private static void regularUserSelectionResult(int userChoice) {
        switchUser(userChoice, 5);
        UIAction result = uiActionMap.userSelectionForRegularUser(userChoice);
        result.execute();
    }

    //need to finalize
    private static void switchUser(int userChoice, int number) {
        if (userChoice == number) {
            String personalCode = logIn();
            if (isUserAdmin(personalCode)) {
                ifAdminLogin();
            } else {
                ifUserLogin();
            }
        }
    }

    private static void adminUserSelectionResult(int userChoice) {
        switchUser(userChoice, 4);
        UIAction result = uiActionMap.userSelectionForAdmin(userChoice);
        result.execute();
    }

    private static int userChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        return scanner.nextInt();
    }
}