package myApp.core.services;

import myApp.core.database.BankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllBankAccountsService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public GetAllBankAccountsResponse execute(GetAllBankAccountsRequest request) {
        List<BankAccount> bankAccounts = bankAccountRepository.getAllBankAccounts();
        return new GetAllBankAccountsResponse(null, bankAccounts);
    }
}
