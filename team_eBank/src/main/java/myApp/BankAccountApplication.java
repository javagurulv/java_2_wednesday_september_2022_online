package myApp;

import myApp.config.BankAccountConfiguration;
import myApp.consoleUI.LogInUIAction;
import myApp.consoleUI.ProgramMenuForAdmin;
import myApp.consoleUI.ProgramMenuForRegularUser;
import myApp.core.services.UserAreAdminService;
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
                executeIfPersonalCodeNotValid(applicationContext);
            } else {
                if (isUserAdmin(applicationContext)) {
                    ifAdminLogin(applicationContext);
                } else {
                    ifUserLogin(applicationContext);
                }
            }
        }
    }

    private static void ifAdminLogin(ApplicationContext applicationContext) {
        ProgramMenuForAdmin adminMenu = applicationContext.getBean(ProgramMenuForAdmin.class);
        adminMenu.printInformationForAdmin();
        int result = adminMenu.userChoice();
        adminMenu.executeSelectedMenuItem(result);
    }

    private static void ifUserLogin(ApplicationContext applicationContext) {
        ProgramMenuForRegularUser userMenu = applicationContext.getBean(ProgramMenuForRegularUser.class);
        userMenu.printInformationForRegularUser();
        int result = userMenu.userChoice();
        userMenu.executeSelectedMenuItem(result);
    }

    private static void logIn(ApplicationContext applicationContext) {
        LogInUIAction uiAction = applicationContext.getBean(LogInUIAction.class);
        uiAction.execute();
    }

    private static boolean isUserAdmin(ApplicationContext applicationContext) {
        UserService userService = applicationContext.getBean(UserService.class);
        UserAreAdminService userAreAdminService = applicationContext.getBean(UserAreAdminService.class);
        return userAreAdminService.isUserAreAdmin(userService.getPersonalCode());
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
    private static void executeIfPersonalCodeNotValid(ApplicationContext applicationContext) {
        System.out.println();
        System.out.println("User not found");
        System.out.println();
        logIn(applicationContext);
    }
}