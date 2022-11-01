package generalPackage.menuModules;

import generalPackage.adminOperationsUI.*;
import generalPackage.dependencyInjection.ApplicationContext;
import generalPackage.dependencyInjection.DIApplicationContextBuilder;

import java.util.Scanner;

public class AdminModule {


//    private static ApplicationContext applicationContext = new ApplicationContext();
        private static ApplicationContext applicationContext =new DIApplicationContextBuilder().build("generalPackage");


//    private static ApplicationContext applicationContext;

//    public AdminModule(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }

    public void executeAdminModule() {
        while (true) {
            printAdminMenu();
            int menuItem = getUserSelection();
            executeAdminMenuItem(menuItem);

        }
    }

    private static void printAdminMenu() {
        System.out.println();
        System.out.println("Please make your choice:");
        System.out.println("1. Add account");
        System.out.println("2. Delete account");
        System.out.println("3. Find account");
        System.out.println("4. Print all accounts");
        System.out.println("5. Find accounts by name");
        System.out.println("6. Exit");
        System.out.println();
    }

    private static int getUserSelection() {
        System.out.println("Enter menu item number to proceed:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }


    private static void executeAdminMenuItem(int menuItem) {
        switch (menuItem) {
            case 1: {
                AddAccountAdminUIAction uiAction = applicationContext.getBean(AddAccountAdminUIAction.class);
                uiAction.execute();
                break;
            }
            case 2: {
                DeleteAccountAdminUIAction uiAction = applicationContext.getBean(DeleteAccountAdminUIAction.class);
                uiAction.execute();
                break;
            }
            case 3: {
                FindUserAdminUIAction uiAction = applicationContext.getBean(FindUserAdminUIAction.class);
                uiAction.execute();
                break;
            }
            case 4: {
                GetAllAccountsAdminUIAction uiAction = applicationContext.getBean(GetAllAccountsAdminUIAction.class);
                uiAction.execute();
                break;
            }
            case 5: {
                SearchAccountsAdminUIAction uiAction = applicationContext.getBean(SearchAccountsAdminUIAction.class);
                uiAction.execute();
                break;
            }
            case 6: {
                ExitAdminUIAction uiAction = applicationContext.getBean(ExitAdminUIAction.class);
                uiAction.execute();
                break;
            }
        }
    }
}
