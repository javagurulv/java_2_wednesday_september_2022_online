package myApp.core.services;

import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.database.DataBase;

import java.util.Map;

public class UserAreAdminService {

    private DataBase dataBase;

    public UserAreAdminService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean isUserAreAdmin(String personalCode) {
        Map<String, BankAccount> bankAccounts = dataBase.getAllBankAccountsMap();
        BankAccount bankAccount = bankAccounts.get(personalCode);
        return bankAccount.getRoles().equals(Roles.Admin);
    }
}
