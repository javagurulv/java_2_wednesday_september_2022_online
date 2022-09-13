package lv.javaguru.java2.atmapp.menuTemporary;

import java.util.Scanner;

class UI_Menu {

    private AccountsTemp account;

	public UI_Menu() {
		this.account = new AccountsTemp(); //it was my temporary class to test menu
	}

	public static void main(String[] args) {
        UI_Menu anotherTry = new UI_Menu();
        if (anotherTry.customerAuthorisation()) {
            operationMenu();
        }
    }

    boolean customerAuthorisation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input your Name/AccountNumber: ");
        String name = scanner.nextLine();
        return account.isExist(name);
    }


    static int operationMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("Please choose operartion: ");
            System.out.println("1. Cash Withdrawal");
            System.out.println("2. Money Transfer");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    withdrawalMenu();
                    break;

                case 2:
                    paymentMenu();
                    break;

                case 3:
                    balance();
                    break;

                case 4:
                    System.exit(0);
            }
        }
    }

    static int withdrawalMenu() {
        int amountToCashOut;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input amount for withdrawal: ");
        amountToCashOut = scanner.nextInt();
        return amountToCashOut;
    }

    static int paymentMenu() {
        int amountToTransfer;
        String recipient;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input amount: ");
        amountToTransfer = scanner.nextInt();
        System.out.println("Please input recipient name/account: ");
        recipient = scanner.nextLine();
        return amountToTransfer;
    }

    static int balance() {

        return 0;
    }

}
