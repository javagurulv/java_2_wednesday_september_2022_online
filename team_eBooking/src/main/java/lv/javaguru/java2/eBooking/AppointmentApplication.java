package lv.javaguru.java2.eBooking;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.database.InMemoryDatabase;
import lv.javaguru.java2.eBooking.console_ui.*;
import lv.javaguru.java2.eBooking.core.services.appointment_service.AddAppointmentService;
import lv.javaguru.java2.eBooking.core.services.appointment_service.GetAllAppointmentsService;
import lv.javaguru.java2.eBooking.core.services.appointment_service.RemoveAppointmentService;
import lv.javaguru.java2.eBooking.core.services.client_service.add.AddClientService;
import lv.javaguru.java2.eBooking.core.services.client_service.add.AddClientValidator;
import lv.javaguru.java2.eBooking.core.services.client_service.print_all.GetAllClientsService;
import lv.javaguru.java2.eBooking.core.services.client_service.remove.RemoveClientService;


import java.util.Scanner;

public class AppointmentApplication {
    private static Database database = new InMemoryDatabase();
    private static AddClientValidator validator= new AddClientValidator();
    private static AddClientService addClientService = new AddClientService(database,validator);
    private static RemoveClientService removeClientService=new RemoveClientService(database);
    private static GetAllClientsService getAllClientsService = new GetAllClientsService(database);
    private static AddAppointmentService addAppointmentService = new AddAppointmentService(database);
    private static RemoveAppointmentService removeAppointmentService = new RemoveAppointmentService(database);
    private static GetAllAppointmentsService getAllAppointmentsService = new GetAllAppointmentsService(database);

    public static UIAction printApplicationMenuUIAction = new PrintApplicationMenuUIAction();
    public static UIAction addClientUIAction  =new AddClientUIAction(addClientService);
    public static UIAction removeClientUIAction = new RemoveClientUIAction(removeClientService);
    public static UIAction printClientUIAction = new PrintClientUIAction(getAllClientsService);
    public static UIAction addAppointmentUIAction = new AddAppointmentUIAction(addAppointmentService);
    public static UIAction removeAppointmentUIAction = new RemoveAppointmentUIAction(removeAppointmentService);
    public static UIAction printAppointmentUIAction = new PrintAppointmentUIAction(getAllAppointmentsService);
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
