package generalPackage.menuModules;

import generalPackage.ApplicationContext;
import generalPackage.adminOperationsUI.*;

import java.util.Scanner;

public class AdminModule {


//    private static Database database = new AccountDatabaseImpl();
//    private static AddAccountServiceValidator addAccountServiceValidator = new AddAccountServiceValidator();
//    private static DeleteAccountServiceValidator deleteAccountServiceValidator = new DeleteAccountServiceValidator();
//    private static FindUserByIDServiceValidator findUserByIDServiceValidator= new FindUserByIDServiceValidator();
//    private static AddAccountService addAccountService = new AddAccountService(database, addAccountServiceValidator);
//    private static DeleteAccountService deleteAccountService = new DeleteAccountService(database, deleteAccountServiceValidator);
//    private static FindUserByIDService findUserByIDService = new FindUserByIDService(database, findUserByIDServiceValidator);
//    private static GetAllAccountsService getAllAccountsService = new GetAllAccountsService(database);
//    private static AdminUIactions addAccountUI = new AddAccountAdminUIAction(addAccountService);
//    private static AdminUIactions deleteAccountUI = new DeleteAccountAdminUIAction(deleteAccountService);
//    private static AdminUIactions findAccountUI = new FindUserAdminUIAction(findUserByIDService);
//    private static AdminUIactions getAllAccountsUI = new GetAllAccountsAdminUIAction(getAllAccountsService);
//
//    private static AdminUIactions exitUIMenu = new ExitAdminUIAction();


    private static ApplicationContext applicationContext = new ApplicationContext();

    public void executeAdminModule() {
        startAdminPanel();
    }

    public static void startAdminPanel(){
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
        System.out.println("5. Return to main menu.");
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
//                addAccountUI.execute();
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
//                findAccountUI.execute();
                uiAction.execute();
                break;
            }
            case 4: {
                GetAllAccountsAdminUIAction uiAction = applicationContext.getBean(GetAllAccountsAdminUIAction.class);
//                getAllAccountsUI.execute();
                uiAction.execute();
                break;
            }
            case 5:{
                AdminMainMenuUIAction uiAction = applicationContext.getBean(AdminMainMenuUIAction.class);
                uiAction.execute();
            }
            case 6: {
                ExitAdminUIAction uiAction = applicationContext.getBean(ExitAdminUIAction.class);
//                exitUIMenu.execute();
                uiAction.execute();
                break;
            }
        }
    }
}
