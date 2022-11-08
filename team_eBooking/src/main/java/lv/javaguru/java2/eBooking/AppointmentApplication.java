package lv.javaguru.java2.eBooking;
import lv.javaguru.java2.eBooking.config.BookingListConfiguration;
import lv.javaguru.java2.eBooking.console_ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;


public class AppointmentApplication {

    public static void main(String[] args) {

        while (true) {
            ApplicationContext applicationContext = applicationContext();
            ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);
            programMenu.printMenu();
            int menuNumber = programMenu.chooseMenuNumber();
            programMenu.executeSelectedMenuNumber(menuNumber);
        }
    }

    private static AnnotationConfigApplicationContext applicationContext(){
        return new AnnotationConfigApplicationContext(BookingListConfiguration.class);
    }

}
