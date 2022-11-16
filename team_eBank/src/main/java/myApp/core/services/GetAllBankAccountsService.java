package myApp.core.services;

import myApp.core.database.BankRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetAllBankAccountsService {
    @Autowired
    private BankRepository bankRepository;

    public GetAllBankAccountsResponse execute(GetAllBankAccountsRequest request) {
        List<BankAccount> bankAccounts = bankRepository.getAllBankAccounts();
        return new GetAllBankAccountsResponse(null, bankAccounts);
    }
}
