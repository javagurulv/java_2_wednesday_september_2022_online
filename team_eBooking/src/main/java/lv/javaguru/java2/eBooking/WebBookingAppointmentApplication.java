package lv.javaguru.java2.eBooking;

import lv.javaguru.java2.eBooking.config.SpringWebConfiguration;
import lv.javaguru.java2.eBooking.console_ui.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebBookingAppointmentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);

        ProgramMenu programMenu = context.getBean(ProgramMenu.class);

        while (true) {
            programMenu.printMenu();
            int menuNumber = programMenu.chooseMenuNumber();
            programMenu.executeSelectedMenuNumber(menuNumber);
        }
    }

}
