package lv.javaguru.java2.atmapp;

import java.util.ArrayList;
import java.util.Scanner;

class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Accounts> accounts = new ArrayList<>();

        Accounts accounts1 = new Accounts("Ivan", 2000, 0);

        while (true) {
            System.out.println("Glad to see you in our ATM system!");
            System.out.println("Please, choose the point to complete.");
            System.out.println("1) Deposit");
            System.out.println("2) Withdraw");
            System.out.println("3) Balance");
            System.out.println("4) Exit  ");
            int user = scanner.nextInt();
            if (user >= 5) {
                System.out.println();
                System.out.println("!!! Error, make another decision. !!!");
                System.out.println();
            } else {
                switch (user) {
                    case 1 -> {
                        System.out.println();
                        System.out.print("How much money you would to deposit? : ");
                        int userDeposit = scanner.nextInt();
                        accounts1.setBalance(accounts1.getBalance() + userDeposit);
                        System.out.println("Deposit was successful.");
                        System.out.println();
                        System.out.println(accounts1);
                        System.out.println();
                    }
                    case 2 -> {
                        System.out.println();
                        System.out.print("How much money you would to withdraw? : ");
                        int userWithdraw = scanner.nextInt();
                        if (userWithdraw > accounts1.getBalance()) {
                            System.out.println("Incorrect sum. Withdraw exceeds balance.");
                            System.out.println();
                            System.out.println(accounts1);
                            System.out.println();
                        } else {
                            System.out.println("Withdraw completed.");
                            accounts1.setBalance(accounts1.getBalance() - userWithdraw);
                            System.out.println();
                            System.out.println(accounts1);
                            System.out.println();
                        }
                    }
                    case 3 -> {
                        System.out.println();
                        System.out.println("Balance history : ");
                        System.out.println(accounts1);
                        System.out.println();
                    }
                    case 4 -> {
                        System.out.println("See ya next time!");
                        System.exit(0);
                    }
                }
            }

        }
    }
}
