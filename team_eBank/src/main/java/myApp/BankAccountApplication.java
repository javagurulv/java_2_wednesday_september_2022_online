package myApp;

import myApp.config.BankAccountConfiguration;
import myApp.consoleUI.AdminOrRegularUserMenu;
import myApp.consoleUI.LogInUIAction;
import myApp.core.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


class BankAccountApplication {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        ApplicationContext applicationContext = createApplicationContext();
        logIn(applicationContext);
        while (true) {
            String personalCode = getPersonalCode(applicationContext);
            if (emptyCheckPersonalCode(personalCode)) {
                executeIfUserNotValid(applicationContext);
            } else {
                printMenu(applicationContext);
            }
        }
    }

    private static void printMenu(ApplicationContext applicationContext) {
        AdminOrRegularUserMenu adminOrRegularUser = new AdminOrRegularUserMenu();
        adminOrRegularUser.execute(applicationContext);
    }

    private static void logIn(ApplicationContext applicationContext) {
        LogInUIAction uiAction = applicationContext.getBean(LogInUIAction.class);
        uiAction.execute();
    }

    private static String getPersonalCode(ApplicationContext applicationContext) {
        UserService userService = applicationContext.getBean(UserService.class);
        return userService.getPersonalCode();
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(BankAccountConfiguration.class);
    }

    private static boolean emptyCheckPersonalCode(String personalCode) {
        return personalCode == null || personalCode.isEmpty();
    }

    private static void executeIfUserNotValid(ApplicationContext applicationContext) {
        System.out.println();
        System.out.println("User not found");
        System.out.println();
        logIn(applicationContext);
    }
}