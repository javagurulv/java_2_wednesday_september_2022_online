package myApp.core.services;
//need to finish
/*

import myApp.core.database.BankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SeeYourAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;
    @InjectMocks
    private SeeYourAccountService service;

    @Test
    public void testSeeAccount() {
        SeeYourAccountRequest request = new SeeYourAccountRequest("0000000-00001");
        Optional<BankAccount> bankAccount = Optional.of(new BankAccount(
                "Example", "ExampleTwo","password", "Roles.Regular_user",0));
        when(bankAccountRepository.seeYourAccount("0000000-00001")).thenReturn(bankAccount);
        SeeYourAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(bankAccountRepository).seeYourAccount("0000000-00001");
    }
}


 */
