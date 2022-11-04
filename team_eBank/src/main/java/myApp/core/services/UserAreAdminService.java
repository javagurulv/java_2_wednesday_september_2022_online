package myApp.core.services;

import myApp.core.database.BankAccountRepository;
import myApp.core.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserAreAdminService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public boolean isUserAreAdmin(String personalCode) {
        List<BankAccount> bankAccounts = bankAccountRepository.getAllBankAccounts();
        Optional<BankAccount> bankAccount = bankAccounts.stream()
                .filter(bankAccount1 -> bankAccount1.getPersonalCode().equals(personalCode))
                .findFirst();
            return bankAccount.get().getRole().equals("Roles.Admin");
    }
}
