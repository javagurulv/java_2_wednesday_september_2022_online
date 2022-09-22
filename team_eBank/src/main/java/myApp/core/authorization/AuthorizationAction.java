package myApp.core.authorization;

import myApp.BankAccount;
import myApp.Roles;
import myApp.consoleUI.UIActionMap;
import java.util.Map;

public class AuthorizationAction {

    private UIActionMap uiActionMap;

    public AuthorizationAction(UIActionMap uiActionMap) {
        this.uiActionMap = uiActionMap;
    }

    public boolean isUserAreAdmin(String personalCode) {
        Map<String, BankAccount> bankAccounts = uiActionMap.getBankAccountsMap();
        BankAccount bankAccount = bankAccounts.get(personalCode);
        return bankAccount.getRoles().equals(Roles.Admin);
    }
}
