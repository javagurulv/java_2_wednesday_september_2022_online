package myApp.core.services;

import myApp.core.database.BankRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;
@Component
@Transactional
public class SeeYourAccountService {
    @Autowired
    private BankRepository bankRepository;

    public SeeYourAccountResponse execute(SeeYourAccountRequest request) {
        Optional<BankAccount> bankAccount = bankRepository.seeYourAccount(request.getPersonalCode());
        return new SeeYourAccountResponse(bankAccount);
    }
}