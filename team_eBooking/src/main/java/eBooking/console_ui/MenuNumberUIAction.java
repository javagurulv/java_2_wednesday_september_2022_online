package eBooking.console_ui;

import java.util.Scanner;

public class MenuNumberUIAction {

    public int executeMenuNumberChoice() {
        System.out.println("Choose menu number to execute");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }
}
