package atmApplication.Menu.InterfaceActions;

import java.util.Scanner;

public class Deposit implements UI_Menu {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, input amount for deposit : ");
        int amountToDeposit = scanner.nextInt();
    }
}