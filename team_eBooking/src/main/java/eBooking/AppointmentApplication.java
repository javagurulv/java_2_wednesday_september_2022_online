package eBooking;

import eBooking.console_ui.*;
import eBooking.database.Database;
import eBooking.database.InMemoryDatabase;
import eBooking.service.AddClientService;

import java.util.Scanner;

public class AppointmentApplication {
    private static Database database = new InMemoryDatabase();
    private static AddClientService addClientService = new AddClientService(database);

    private static UIAction printApplicationMenuUIAction = new PrintApplicationMenuUIAction();
    private static UIAction addClientUIAction  =new AddClientUIAction(addClientService);
    public static UIAction removeClientUIAction = new RemoveClientUIAction(database);
    public static UIAction printClientUIAction = new PrintClientUIAction(database);
    public static UIAction addAppointmentUIAction = new AddAppointmentUIAction(database);
    public static UIAction removeAppointmentUIAction = new RemoveAppointmentUIAction(database);
    public static UIAction printAppointmentUIAction = new PrintAppointmentUIAction(database);
    public static UIAction exitApplicationUIAction = new ExitApplicationUIAction();

    public static void main(String[] args) {

        while (true) {
            printApplicationMenuUIAction.execute();
            switch (chooseMenuNumber()) {
                case 1: {
                    addClientUIAction.execute();
                    break;
                }
                case 2: {
                    removeClientUIAction.execute();
                    break;
                }
                case 3: {
                    printClientUIAction.execute();
                    break;
                }
                case 4: {
                    addAppointmentUIAction.execute();
                    break;
                }
                case 5: {
                    removeAppointmentUIAction.execute();
                    break;
                }
                case 6: {
                    printAppointmentUIAction.execute();
                    break;
                }
                case 7: {
                    exitApplicationUIAction.execute();
                }
            }
        }
    }
    public static int chooseMenuNumber() {
        System.out.println("Choose menu number to execute");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }
}
