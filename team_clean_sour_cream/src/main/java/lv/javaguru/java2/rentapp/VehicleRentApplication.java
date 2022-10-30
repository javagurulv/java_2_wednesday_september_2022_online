package lv.javaguru.java2.rentapp;

import lv.javaguru.java2.rentapp.config.VehicleRentConfiguration;
import lv.javaguru.java2.rentapp.console_UI.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class VehicleRentApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = createApplicationContext();

        ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);

        while (true) {
            try {
                programMenu.printMainMenu();
                int userChoice = programMenu.getMenuNumberFromUser();
                if (userChoice > programMenu.getMenuNumberToUIActionMapSize() || userChoice < 1) {
                    System.out.println("You must enter an integer that corresponds with a number from program menu (1 - " + programMenu.getMenuNumberToUIActionMapSize() + ")");
                } else {
                    programMenu.executeSelectedMenuItem(userChoice);
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("You must enter an integer!");
            }
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(VehicleRentConfiguration.class);
    }
}

