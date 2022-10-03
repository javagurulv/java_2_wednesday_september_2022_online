package myApp.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import myApp.core.domain.BankAccount;

import java.util.Optional;

@AllArgsConstructor
@Getter
public class SeeYourAccountResponse {

    private Optional<BankAccount> bankAccount;
}
