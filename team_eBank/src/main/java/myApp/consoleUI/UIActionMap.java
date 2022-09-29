package myApp.consoleUI;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.services.*;
import myApp.core.services.validators.*;

import java.util.HashMap;
import java.util.Map;

public class UIActionMap {

    private static DataBase dataBase = new InMemoryDatabaseImpl();
    private static UserAreAdminService userAreAdminService = new UserAreAdminService(dataBase);
    private static UserService userService = new UserService(dataBase);
    private static AddBankAccountValidator validator = new AddBankAccountValidator();
    private static AddBankAccountService service = new AddBankAccountService(dataBase, validator);

    private static UIAction addBankAccountUIAction = new AddBankAccountUIAction(service);
    private static RemoveBankAccountValidator removeBankAccountValidator = new RemoveBankAccountValidator();
    private static RemoveBankAccountService removeBankAccountService = new RemoveBankAccountService(dataBase
            , removeBankAccountValidator);
    private static UIAction removeBankAccountUIAction = new RemoveBankAccountUIAction(removeBankAccountService);
    private static UIAction getAllAccountsUIAction = new GetAllAccountsUIAction(dataBase);
    private static MoneyTransferValidator moneyTransferValidator = new MoneyTransferValidator();
    private static MoneyTransferService moneyTransferService = new MoneyTransferService(dataBase
            , moneyTransferValidator);
    private static UIAction moneyTransfer = new MoneyTransferUIAction(moneyTransferService, userService);
    private static OpenAccountValidator openAccountValidator = new OpenAccountValidator();
    private static OpenAccountService accountService = new OpenAccountService(dataBase, openAccountValidator);
    private static UIAction openAccount = new OpenAccountUIAction(accountService, userService);
    private static CloseAccountValidator closeAccountValidator = new CloseAccountValidator();
    private static CloseAccountService closeAccountService = new CloseAccountService(dataBase, closeAccountValidator);
    private static CloseAccountUIAction closeAccountUIAction = new CloseAccountUIAction(closeAccountService, userService);
    private static SeeYourAccountService seeYourAccountService = new SeeYourAccountService(dataBase);
    private static SeeYourAccountUIAction seeYourAccountUIAction = new SeeYourAccountUIAction(seeYourAccountService, userService);
    private static SwitchUserService switchUserService = new SwitchUserService(userService);
    private static SwitchUserUIAction switchUserUIAction = new SwitchUserUIAction(switchUserService);
    private static UIAction exit = new ExitUIAction();
    private Map<Integer, UIAction> uiActionMap = new HashMap<>();

    public UIAction userSelectionForRegularUser(int userChoice) {
        uiActionMap.put(1, moneyTransfer);
        uiActionMap.put(2, openAccount);
        uiActionMap.put(3, closeAccountUIAction);
        uiActionMap.put(4, seeYourAccountUIAction);
        uiActionMap.put(5, switchUserUIAction);
        uiActionMap.put(6, exit);
        uiActionMap.put(7, getAllAccountsUIAction);
        return uiActionMap.get(userChoice);
    }

    public UIAction userSelectionForAdmin(int userChoice) {
        uiActionMap.put(1, getAllAccountsUIAction);
        uiActionMap.put(2, addBankAccountUIAction);
        uiActionMap.put(3, removeBankAccountUIAction);
        uiActionMap.put(4, switchUserUIAction);
        uiActionMap.put(5, exit);
        return uiActionMap.get(userChoice);
    }

    public boolean isUserAdmin(String personalCode) {
        return userAreAdminService.isUserAreAdmin(personalCode);
    }

    public String getPersonalCode() {
        return userService.getPersonalCode();
    }

    public String logIn(String personalCode) {
        return userService.logIn(personalCode);
    }
}
