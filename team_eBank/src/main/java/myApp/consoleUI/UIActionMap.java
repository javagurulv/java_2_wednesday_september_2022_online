package myApp.consoleUI;

import myApp.BankAccount;
import myApp.User;
import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.services.*;
import myApp.core.services.validators.AddBankAccountValidator;
import myApp.core.services.validators.MoneyTransferValidator;
import myApp.core.services.validators.RemoveBankAccountValidator;

import java.util.HashMap;
import java.util.Map;

public class UIActionMap {

    private static DataBase dataBase = new InMemoryDatabaseImpl();
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
    private static UIAction moneyTransfer = new MoneyTransferUIAction(moneyTransferService);
    private static AddAccountService accountService = new AddAccountService(dataBase);
    private static UIAction openAccount = new AddAccountUIAction(accountService);
    private static UIAction exit = new ExitUIAction();
    private Map<Integer, UIAction> uiActionMap = new HashMap<>();
    public UIAction userSelectionForRegularUser(int userChoice) {
        uiActionMap.put(1, moneyTransfer);
        uiActionMap.put(2, openAccount);
        uiActionMap.put(3,getAllAccountsUIAction);
        uiActionMap.put(4, exit);
        return uiActionMap.get(userChoice);
    }

    public UIAction userSelectionForAdmin(int userChoice) {
        uiActionMap.put(1,getAllAccountsUIAction);
        uiActionMap.put(2, addBankAccountUIAction);
        uiActionMap.put(3, removeBankAccountUIAction);
        uiActionMap.put(4,moneyTransfer);
        uiActionMap.put(5,exit);
        return uiActionMap.get(userChoice);
    }

    public Map<String , BankAccount> getBankAccountsMap() {
        return dataBase.getAllBankAccountsMap();
    }
}
