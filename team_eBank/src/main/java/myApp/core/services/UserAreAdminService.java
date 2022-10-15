package myApp.core.services;

import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.database.DataBase;
import myApp.core.responses.CoreError;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.List;
import java.util.Optional;
@DIComponent
public class UserAreAdminService {

    @DIDependency
    private DataBase dataBase;

    public boolean isUserAreAdmin(String personalCode) {
        List<BankAccount> bankAccounts = dataBase.getAllBankAccounts();
        Optional<BankAccount> bankAccount = bankAccounts.stream()
                .filter(bankAccount1 -> bankAccount1.getPersonalCode().equals(personalCode))
                .findFirst();
            return bankAccount.get().getRole().equals(Roles.Admin);
    }
}
