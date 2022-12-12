package myApp;

//import myApp.consoleUI.AdminOrRegularUserMenu;
import myApp.consoleUI.LogInUIAction;
import myApp.core.services.authentication.UserService;
import myApp.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
class WebBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebConfiguration.class);
        //run();
    }

    private static void run() {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);
        logIn(context);
        while (true) {
            String personalCode = getPersonalCode(context);
            if (emptyCheckPersonalCode(personalCode)) {
                executeIfUserNotValid(context);
            } else {
                //printMenu(context);
            }
        }
    }
/*
    private static void printMenu(ApplicationContext applicationContext) {
        AdminOrRegularUserMenu adminOrRegularUser = new AdminOrRegularUserMenu();
        adminOrRegularUser.execute(applicationContext);
    }

 */

    private static void logIn(ApplicationContext applicationContext) {
        LogInUIAction uiAction = applicationContext.getBean(LogInUIAction.class);
        uiAction.execute();
    }

    private static String getPersonalCode(ApplicationContext applicationContext) {
        UserService userService = applicationContext.getBean(UserService.class);
        return userService.getPersonalCode();
    }
    private static boolean emptyCheckPersonalCode(String personalCode) {
        return personalCode == null || personalCode.isEmpty();
    }

    private static void executeIfUserNotValid(ApplicationContext applicationContext) {
        System.out.println("User not found");
        System.out.println();
        logIn(applicationContext);
    }
}