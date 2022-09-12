package fitness_club;

import java.util.Scanner;

class FitnessClubApplication {
    public static void main(String[] args) {
        while (true) {
            menu();
            int menuNumber = getMenuNumberFromUser();
            executeSelectedMenuItem(menuNumber);
        }
    }

    private static void menu() {
        System.out.println("1 - Register");
        System.out.println("2 - Sign in");
        System.out.println("3 - Exit");
    }

    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void executeSelectedMenuItem(int selectedMenu) {
        switch (selectedMenu) {
            case 1 -> registerForm();
            case 2 -> signInMenu();
            case 3 -> exitProgramAction();
        }
    }

    private static void registerForm() {
        System.out.println("Registration success");
    }

    private static void signInMenu() {
        System.out.println("You sign in as - ");
    }

    private static void exitProgramAction() {
        System.out.println("Good by!");
        System.exit(0);
    }
}
