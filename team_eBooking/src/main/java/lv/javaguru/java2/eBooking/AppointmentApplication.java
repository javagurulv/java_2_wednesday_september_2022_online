package lv.javaguru.java2.eBooking;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.database.InMemoryDatabase;
import lv.javaguru.java2.eBooking.console_ui.*;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentAddService;
import lv.javaguru.java2.eBooking.core.services.validators.*;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentGetAllService;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentRemoveService;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentSearchService;
import lv.javaguru.java2.eBooking.core.services.client.ClientAddService;
import lv.javaguru.java2.eBooking.core.services.client.ClientGetAllService;
import lv.javaguru.java2.eBooking.core.services.client.ClientRemoveService;
import lv.javaguru.java2.eBooking.core.services.client.ClientSearchService;


import java.util.Scanner;

public class AppointmentApplication {

 private static ApplicationContext appContext = new ApplicationContext();

    public static void main(String[] args) {

        while (true) {
            PrintApplicationMenuUIAction printApplicationMenuUIAction =appContext.getBean(PrintApplicationMenuUIAction.class);
            printApplicationMenuUIAction.execute();
            switch (chooseMenuNumber()) {
                case 1: {

                    AddClientUIAction addClientUIAction = appContext.getBean(AddClientUIAction.class);
                    addClientUIAction.execute();
                    break;
                }
                case 2: {
                    RemoveClientUIAction removeClientUIAction = appContext.getBean(RemoveClientUIAction.class);
                    removeClientUIAction.execute();
                    break;
                }
                case 3: {
                    PrintClientUIAction printClientUIAction = appContext.getBean(PrintClientUIAction.class);
                    printClientUIAction.execute();
                    break;
                }
                case 4: {
                    SearchClientUIAction searchClientUIAction = appContext.getBean(SearchClientUIAction.class);
                    searchClientUIAction.execute();
                    break;
                }
                case 5: {
                    AddAppointmentUIAction addAppointmentUIAction = appContext.getBean(AddClientUIAction.class);
                    addAppointmentUIAction.execute();
                    break;
                }
                case 6: {
                    RemoveAppointmentUIAction removeAppointmentUIAction=appContext.getBean(RemoveClientUIAction.class);
                    removeAppointmentUIAction.execute();
                    break;
                }
                case 7: {
                    PrintAppointmentUIAction printAppointmentUIAction = appContext.getBean(PrintAppointmentUIAction.class);
                    printAppointmentUIAction.execute();
                    break;
                }
                case 8: {
                    SearchAppointmentUIAction searchAppointmentUIAction = appContext.getBean(SearchAppointmentUIAction.class);
                    searchAppointmentUIAction.execute();
                    break;
                }
                case 9: {
                    ExitApplicationUIAction exitApplicationUIAction = appContext.getBean(ExitApplicationUIAction.class);
                    exitApplicationUIAction.execute();
                    break;
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
