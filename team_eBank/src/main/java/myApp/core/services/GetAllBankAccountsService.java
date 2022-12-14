package myApp.core.services;


import myApp.core.database.jpa.JpaBankAccountRepository;
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
    private JpaBankAccountRepository bankRepository;

    public GetAllBankAccountsResponse execute(GetAllBankAccountsRequest request) {
        List<BankAccount> bankAccounts = bankRepository.findAll();
        return new GetAllBankAccountsResponse(null, bankAccounts);
    }
}


