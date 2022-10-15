package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserAreAdminService {

    @Autowired
    private DataBase dataBase;

    public boolean isUserAreAdmin(String personalCode) {
        List<BankAccount> bankAccounts = dataBase.getAllBankAccounts();
        Optional<BankAccount> bankAccount = bankAccounts.stream()
                .filter(bankAccount1 -> bankAccount1.getPersonalCode().equals(personalCode))
                .findFirst();
            return bankAccount.get().getRole().equals(Roles.Admin);
    }
}
