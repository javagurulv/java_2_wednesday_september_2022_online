package eBooking;

import eBooking.console_ui.*;

public class AppointmentApplication {
    public static void main(String[] args) {

        Database database = new InMemoryDatabase();
        MenuNumberUIAction menuNumberUIAction=new MenuNumberUIAction();
        PrintApplicationMenuUIAction printApplicationMenuUIAction = new PrintApplicationMenuUIAction();
        AddClientUIAction addClientUIAction = new AddClientUIAction(database);
        RemoveClientUIAction removeClientUIAction = new RemoveClientUIAction(database);
        PrintClientUIAction printClientUIAction = new PrintClientUIAction(database);
        AddAppointmentUIAction addAppointmentUIAction = new AddAppointmentUIAction(database);
        RemoveAppointmentUIAction removeAppointmentUIAction = new RemoveAppointmentUIAction(database);
        PrintAppointmentUIAction printAppointmentUIAction = new PrintAppointmentUIAction(database);
        ExitApplicationUIAction exitApplicationUIAction = new ExitApplicationUIAction();

        while (true) {
            printApplicationMenuUIAction.executePrintApplicationMenu();
            switch (menuNumberUIAction.executeMenuNumberChoice()) {
                case 1: {
                    addClientUIAction.executeAddClient();
                    break;
                }
                case 2: {
                    removeClientUIAction.executeClientRemove();
                    break;
                }
                case 3: {
                    printClientUIAction.executePrintClients();
                    break;
                }
                case 4: {
                    addAppointmentUIAction.executeAddAppointment();
                    break;
                }
                case 5: {
                    removeAppointmentUIAction.executeRemoveAppointment();
                    break;
                }
                case 6: {
                    printAppointmentUIAction.executePrintAppointments();
                    break;
                }
                case 7: {
                    exitApplicationUIAction.executeExitAnApplication();
                }
            }
        }
    }
}
