package lv.javaguru.java2.eBooking;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.database.InMemoryDatabase;
import lv.javaguru.java2.eBooking.console_ui.*;
import lv.javaguru.java2.eBooking.core.services.appointment.AddAppointmentService;
import lv.javaguru.java2.eBooking.core.services.validators.AddAppointmentValidator;
import lv.javaguru.java2.eBooking.core.services.appointment.GetAllAppointmentsService;
import lv.javaguru.java2.eBooking.core.services.appointment.RemoveAppointmentService;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentSearchRequestValidator;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentSearchService;
import lv.javaguru.java2.eBooking.core.services.client.AddClientService;
import lv.javaguru.java2.eBooking.core.services.validators.AddClientValidator;
import lv.javaguru.java2.eBooking.core.services.client.GetAllClientsService;
import lv.javaguru.java2.eBooking.core.services.client.RemoveClientService;
import lv.javaguru.java2.eBooking.core.services.validators.ClientSearchRequestValidator;
import lv.javaguru.java2.eBooking.core.services.client.ClientSearchService;


import java.util.Scanner;

public class AppointmentApplication {

    private static Database database = new InMemoryDatabase();

    private static AddClientValidator clientValidator = new AddClientValidator();
    private static ClientSearchRequestValidator validator = new ClientSearchRequestValidator();
    private static AppointmentSearchRequestValidator appointmentSearchRequestvalidator =
            new AppointmentSearchRequestValidator();
    private static AddAppointmentValidator appointmentValidator = new AddAppointmentValidator();

    private static AddClientService addClientService = new AddClientService(database, clientValidator);
    private static RemoveClientService removeClientService = new RemoveClientService(database);
    private static GetAllClientsService getAllClientsService = new GetAllClientsService(database);
    private static ClientSearchService clientSearchService = new ClientSearchService(database, validator);
    private static AddAppointmentService addAppointmentService = new AddAppointmentService(database, appointmentValidator);
    private static RemoveAppointmentService removeAppointmentService = new RemoveAppointmentService(database);
    private static GetAllAppointmentsService getAllAppointmentsService = new GetAllAppointmentsService(database);
    private static AppointmentSearchService appointmentSearchService = new AppointmentSearchService(database, appointmentSearchRequestvalidator);

    public static UIAction searchAppointmentUIAction = new SearchAppointmentUIAction(appointmentSearchService);
    public static UIAction searchClientUIAction = new SearchClientUIAction(clientSearchService);
    public static UIAction printApplicationMenuUIAction = new PrintApplicationMenuUIAction();
    public static UIAction addClientUIAction = new AddClientUIAction(addClientService);
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
                    searchClientUIAction.execute();
                    break;
                }
                case 5: {
                    addAppointmentUIAction.execute();
                    break;
                }
                case 6: {
                    removeAppointmentUIAction.execute();
                    break;
                }
                case 7: {
                    printAppointmentUIAction.execute();
                    break;
                }
                case 8: {
                    searchAppointmentUIAction.execute();
                    break;
                }
                case 9: {
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
