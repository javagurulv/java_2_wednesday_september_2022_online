package lv.javaguru.java2.atmapp.menuTemporary.Actions.InterfaceActions;

import java.util.Scanner;

public class Withdraw implements UI_Menu {

    @Override
    public int execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, input amount for withdraw : ");
        return scanner.nextInt();
    }
}
