package eBooking;

import eBooking.console_ui.*;
import eBooking.database.Database;
import eBooking.database.InMemoryDatabase;
import eBooking.service.AddClientService;

import java.util.Scanner;

public class AppointmentApplication {
    public static void main(String[] args) {

        Database database = new InMemoryDatabase();
        PrintApplicationMenuUIAction printApplicationMenuUIAction = new PrintApplicationMenuUIAction();
        AddClientService addClientService = new AddClientService(database);
        AddClientUIAction addClientUIAction = new AddClientUIAction(addClientService);
        RemoveClientUIAction removeClientUIAction = new RemoveClientUIAction(database);
        PrintClientUIAction printClientUIAction = new PrintClientUIAction(database);
        AddAppointmentUIAction addAppointmentUIAction = new AddAppointmentUIAction(database);
        RemoveAppointmentUIAction removeAppointmentUIAction = new RemoveAppointmentUIAction(database);
        PrintAppointmentUIAction printAppointmentUIAction = new PrintAppointmentUIAction(database);
        ExitApplicationUIAction exitApplicationUIAction = new ExitApplicationUIAction();


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
