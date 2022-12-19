package lv.javaguru.java2.repo_men_inc;

import lv.javaguru.java2.repo_men_inc.config.RepoMenIncConfiguration;
import lv.javaguru.java2.repo_men_inc.console_ui.ProgramMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RepoMenIncApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = createApplicationContext();
        while (true) {
            ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);
            try {
                programMenu.printMenu();
                int userChoice = programMenu.getUserChoice();
                programMenu.executeUsersChoice(userChoice);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("INVALID INPUT!");
            }
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(RepoMenIncConfiguration.class);
    }
}
