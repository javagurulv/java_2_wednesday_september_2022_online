package myApp.consoleUI;

import myApp.core.services.authentication.UserAreAdminService;
import myApp.core.services.authentication.UserService;
import org.springframework.context.ApplicationContext;

public class AdminOrRegularUserMenu {


    public void execute(ApplicationContext applicationContext) {
        if (isUserAdmin(applicationContext)) {
            ifAdminLogin(applicationContext);
        } else {
            ifUserLogin(applicationContext);
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

    private static boolean isUserAdmin(ApplicationContext applicationContext) {
        UserService userService = applicationContext.getBean(UserService.class);
        UserAreAdminService userAreAdminService = applicationContext.getBean(UserAreAdminService.class);
        return userAreAdminService.isUserAreAdmin(userService.getPersonalCode());
    }
}
