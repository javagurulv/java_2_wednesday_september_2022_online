package myApp.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import myApp.core.domain.BankAccount;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class GetAllBankAccountsResponse extends CoreResponse {

    private List<BankAccount> bankAccounts;

}
