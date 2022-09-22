package atmApplication.Menu;

import java.util.Scanner;

public class Transfer {
    void transfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input recipient name/account : ");
        String recipient = scanner.nextLine();
        System.out.print("Please input amount : ");
        int amountToTransfer = scanner.nextInt();
    }
}
