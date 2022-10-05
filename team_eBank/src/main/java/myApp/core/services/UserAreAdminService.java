package myApp.core.services;

import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.database.DataBase;
import myApp.core.responses.CoreError;

import java.util.List;
import java.util.Optional;

public class UserAreAdminService {

    private DataBase dataBase;

    public UserAreAdminService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean isUserAreAdmin(String personalCode) {
        List<BankAccount> bankAccounts = dataBase.getAllBankAccounts();
        Optional<BankAccount> bankAccount = bankAccounts.stream()
                .filter(bankAccount1 -> bankAccount1.getPersonalCode().equals(personalCode))
                .findFirst();
            return bankAccount.get().getRole().equals(Roles.Admin);
    }
}
