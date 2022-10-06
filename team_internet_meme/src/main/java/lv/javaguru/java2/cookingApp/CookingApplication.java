package lv.javaguru.java2.cookingApp;

import lv.javaguru.java2.cookingApp.config.CookingAppConfiguration;
import lv.javaguru.java2.cookingApp.consoleui.ProgramMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class CookingApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = createApplicationContext();
        ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);
        while (true) {
            programMenu.print();
            int menuNumber = programMenu.getMenuNumberFromUser();
            programMenu.executeSelectedMenuItem(menuNumber);
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(CookingAppConfiguration.class);
    }

}
