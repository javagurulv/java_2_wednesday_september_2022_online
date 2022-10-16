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

    private static ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(BankAccountConfiguration.class);

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        logIn();
        while (true) {
            String personalCode = getPersonalCode();
            if (personalCode == null || personalCode.isEmpty()) {
                System.out.println();
                System.out.println("User not found");
                System.out.println();
                logIn();
            } else {
                if (isUserAdmin()) {
                    ifAdminLogin();
                } else {
                    ifUserLogin();
                }
            }
        }
    }

    private static void ifAdminLogin() {
        ProgramMenuForAdmin adminMenu = applicationContext.getBean(ProgramMenuForAdmin.class);
        adminMenu.printInformationForAdmin();
        int result = adminMenu.userChoice();
        adminMenu.executeSelectedMenuItem(result);
    }

    private static void ifUserLogin() {
        ProgramMenuForRegularUser userMenu = applicationContext.getBean(ProgramMenuForRegularUser.class);
        userMenu.printInformationForRegularUser();
        int result = userMenu.userChoice();
        userMenu.executeSelectedMenuItem(result);
    }

    private static void logIn() {
        LogInUIAction uiAction = applicationContext.getBean(LogInUIAction.class);
        uiAction.execute();
    }

    private static boolean isUserAdmin() {
        UserService userService = applicationContext.getBean(UserService.class);
        UserAreAdminService userAreAdminService = applicationContext.getBean(UserAreAdminService.class);
        return userAreAdminService.isUserAreAdmin(userService.getPersonalCode());
    }

    private static String getPersonalCode() {
        UserService userService = applicationContext.getBean(UserService.class);
        return userService.getPersonalCode();
    }
}