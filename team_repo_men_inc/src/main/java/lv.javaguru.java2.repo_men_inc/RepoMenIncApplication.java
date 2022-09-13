package lv.javaguru.java2.repo_men_inc;

import java.util.HashMap;
import java.util.Scanner;

public class RepoMenIncApplication {
    public static void main(String[] args) {
        HashMap<String, Debtor> debtors = new HashMap<>();

        while (true) {
            System.out.println("Program menu:");
            System.out.println("1. Add new debtor to list");
            System.out.println("2. Add harvested item to debtors list");
            System.out.println("3. Delete debtor from list");
            System.out.println("4. Show all debtors in the list");
            System.out.println("5. Exit");

            System.out.println("===================================================");

            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1 -> {
                    System.out.println("Enter debtors name: ");
                    String debtorsName = scanner.nextLine();
                    Debtor debtor = new Debtor(debtorsName);
                    debtors.put(debtorsName, debtor);
                    System.out.println("New Debtor was added to list.");
                }
                case 2 -> {
                    System.out.println("Enter debtors name: ");
                    String debtorsName = scanner.nextLine();
                    System.out.println("Enter harvested item");
                    String harvestedItem = scanner.nextLine();
                    debtors.get(debtorsName).addIem(harvestedItem);
                    System.out.println("Harvested Item added to debtors list.");
                }
                case 3 -> {
                    System.out.println("Enter debtors name: ");
                    String debtorsName = scanner.nextLine();
                    debtors.remove(debtorsName);
                    System.out.println("Debtor was removed from list.");
                }
                case 4 -> {
                    System.out.println("Debtors list: ");
                    debtors.forEach((key, value) -> System.out.println(value));
                    System.out.println("Debtor list end.");
                }
                case 5 -> {
                    System.out.println("Good by!");
                    System.exit(0);
                }
            }
            System.out.println("===================================================");
        }
    }
}
