package myApp.core.services.authentication;
/*
import myApp.core.database.BankRepository;
import myApp.core.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
//@Componentomponent
//@Transactionalnsactional
public class UserAreAdminService {

    @Autowired
    private BankRepository bankRepository;

    public boolean isUserAreAdmin(String personalCode) {
        List<BankAccount> bankAccounts = bankRepository.getAllBankAccounts();
        Optional<BankAccount> bankAccount = bankAccounts.stream()
                .filter(bankAccount1 -> bankAccount1.getPersonalCode().equals(personalCode))
                .findFirst();
            return bankAccount.get().getRole().equals("Roles.Admin");
    }
}

 */
