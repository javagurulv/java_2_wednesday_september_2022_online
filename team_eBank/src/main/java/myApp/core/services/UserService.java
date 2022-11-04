package myApp.core.services;

import myApp.core.database.BankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    private String personalCode;
    private String password;

    public boolean logIn(String personalCode, String password) {
        List<User> userList = bankAccountRepository.getAllUsers();
        Optional<User> user = userList.stream()
                .filter(user1 -> user1.getPersonalCode().equals(personalCode))
                .filter(user1 -> user1.getPassword().equals(password))
                .findFirst();

        if (user.isPresent()) {
            List<BankAccount> bankAccounts = bankAccountRepository.getAllBankAccounts();
            Optional<BankAccount> result = bankAccounts.stream()
                    .filter(bankAccount -> bankAccount.getPersonalCode().equals(user.get().getPersonalCode()))
                    .findFirst();
            if (result.isPresent()) {
                setPersonalCode(personalCode);
                setPassword(password);
                return true;
            }
            return false;
        }
        return false;
    }

    public void logOut() {
        if (this.personalCode != null && this.password != null) {
            this.personalCode = null;
            this.password = null;
        }
    }

    public String getPersonalCode() {
        return this.personalCode;
    }

   private void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
