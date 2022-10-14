package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.Optional;

@DIComponent
public class UserService {
    @DIDependency
    private DataBase dataBase;
    private String personalCode;
    private String password;

    public boolean logIn(String personalCode, String password) {

        Optional<BankAccount> result = dataBase.getAllBankAccounts().stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(personalCode))
                .filter(bankAccount -> bankAccount.getPassword().equals(password))
                .findFirst();
        if (result.isPresent()) {
            setPersonalCode(personalCode);
            setPassword(password);
            return true;
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
