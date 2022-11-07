package lv.javaguru.java2.eBooking;
import lv.javaguru.java2.eBooking.config.BookingListConfiguration;
import lv.javaguru.java2.eBooking.console_ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;


public class AppointmentApplication {

private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BookingListConfiguration.class);
    public static void main(String[] args) {

        while (true) {
            PrintApplicationMenuUIAction printApplicationMenuUIAction =applicationContext.getBean(PrintApplicationMenuUIAction.class);
            printApplicationMenuUIAction.execute();
            switch (chooseMenuNumber()) {
                case 1: {

                    AddClientUIAction addClientUIAction = applicationContext.getBean(AddClientUIAction.class);
                    addClientUIAction.execute();
                    break;
                }
                case 2: {
                    RemoveClientUIAction removeClientUIAction = applicationContext.getBean(RemoveClientUIAction.class);
                    removeClientUIAction.execute();
                    break;
                }
                case 3: {
                    PrintClientUIAction printClientUIAction = applicationContext.getBean(PrintClientUIAction.class);
                    printClientUIAction.execute();
                    break;
                }
                case 4: {
                    SearchClientUIAction searchClientUIAction = applicationContext.getBean(SearchClientUIAction.class);
                    searchClientUIAction.execute();
                    break;
                }
                case 5: {
                    AddAppointmentUIAction addAppointmentUIAction = applicationContext.getBean(AddAppointmentUIAction.class);
                    addAppointmentUIAction.execute();
                    break;
                }
                case 6: {
                    RemoveAppointmentUIAction removeAppointmentUIAction=applicationContext.getBean(RemoveAppointmentUIAction.class);
                    removeAppointmentUIAction.execute();
                    break;
                }
                case 7: {
                    PrintAppointmentUIAction printAppointmentUIAction = applicationContext.getBean(PrintAppointmentUIAction.class);
                    printAppointmentUIAction.execute();
                    break;
                }
                case 8: {
                    SearchAppointmentUIAction searchAppointmentUIAction = applicationContext.getBean(SearchAppointmentUIAction.class);
                    searchAppointmentUIAction.execute();
                    break;
                }
                case 9: {
                    ExitApplicationUIAction exitApplicationUIAction = applicationContext.getBean(ExitApplicationUIAction.class);
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
