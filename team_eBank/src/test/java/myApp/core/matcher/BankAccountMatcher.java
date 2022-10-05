package myApp.core.matcher;

import lombok.AllArgsConstructor;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import org.mockito.ArgumentMatcher;

@AllArgsConstructor
public class BankAccountMatcher implements ArgumentMatcher<BankAccount> {

        private String name;
        private String surname;
        private String password;
        private Roles role;
        private String personalCode;

        @Override
        public boolean matches(BankAccount bankAccount) {
                return bankAccount.getName().equals(name)
                        && bankAccount.getSurname().equals(surname)
                        && bankAccount.getPassword().equals(password)
                        && bankAccount.getRole().equals(role)
                        && bankAccount.getPersonalCode().equals(personalCode);
        }
}
