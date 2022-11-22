package lv.javaguru.java2.cookingApp;

import lv.javaguru.java2.cookingApp.consoleui.ProgramMenu;
import lv.javaguru.java2.cookingApp.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class WebCookingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);

        ProgramMenu programMenu = context.getBean(ProgramMenu.class);

        while (true) {
            try {
                programMenu.print();
                int menuNumber = programMenu.getMenuNumberFromUser();
                programMenu.executeSelectedMenuItem(menuNumber);
            } catch (NumberFormatException e) {
                System.out.println("You have to enter a number!");
            }
        }
    }

}
