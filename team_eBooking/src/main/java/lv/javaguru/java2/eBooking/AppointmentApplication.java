package lv.javaguru.java2.eBooking;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.database.InMemoryDatabase;
import lv.javaguru.java2.eBooking.console_ui.*;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentAddService;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentAddValidator;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentGetAllService;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentRemoveService;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentSearchValidator;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentSearchService;
import lv.javaguru.java2.eBooking.core.services.client.ClientAddService;
import lv.javaguru.java2.eBooking.core.services.validators.ClientAddValidator;
import lv.javaguru.java2.eBooking.core.services.client.ClientGetAllService;
import lv.javaguru.java2.eBooking.core.services.client.ClientRemoveService;
import lv.javaguru.java2.eBooking.core.services.validators.ClientSearchValidator;
import lv.javaguru.java2.eBooking.core.services.client.ClientSearchService;


import java.util.Scanner;

public class AppointmentApplication {

    private static Database database = new InMemoryDatabase();

    private static ClientAddValidator clientValidator = new ClientAddValidator();
    private static ClientSearchValidator validator = new ClientSearchValidator();
    private static AppointmentSearchValidator appointmentSearchRequestvalidator =
            new AppointmentSearchValidator();
    private static AppointmentAddValidator appointmentValidator = new AppointmentAddValidator();

    private static ClientAddService clientAddService = new ClientAddService(database, clientValidator);
    private static ClientRemoveService clientRemoveService = new ClientRemoveService(database);
    private static ClientGetAllService clientGetAllService = new ClientGetAllService(database);
    private static ClientSearchService clientSearchService = new ClientSearchService(database, validator);
    private static AppointmentAddService appointmentAddService = new AppointmentAddService(database, appointmentValidator);
    private static AppointmentRemoveService appointmentRemoveService = new AppointmentRemoveService(database);
    private static AppointmentGetAllService appointmentGetAllService = new AppointmentGetAllService(database);
    private static AppointmentSearchService appointmentSearchService = new AppointmentSearchService(database, appointmentSearchRequestvalidator);

    public static UIAction searchAppointmentUIAction = new SearchAppointmentUIAction(appointmentSearchService);
    public static UIAction searchClientUIAction = new SearchClientUIAction(clientSearchService);
    public static UIAction printApplicationMenuUIAction = new PrintApplicationMenuUIAction();
    public static UIAction addClientUIAction = new AddClientUIAction(clientAddService);
    public static UIAction removeClientUIAction = new RemoveClientUIAction(clientRemoveService);
    public static UIAction printClientUIAction = new PrintClientUIAction(clientGetAllService);
    public static UIAction addAppointmentUIAction = new AddAppointmentUIAction(appointmentAddService);
    public static UIAction removeAppointmentUIAction = new RemoveAppointmentUIAction(appointmentRemoveService);
    public static UIAction printAppointmentUIAction = new PrintAppointmentUIAction(appointmentGetAllService);
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
