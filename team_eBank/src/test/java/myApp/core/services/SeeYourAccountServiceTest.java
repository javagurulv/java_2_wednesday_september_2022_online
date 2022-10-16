package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.domain.Account;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SeeYourAccountServiceTest {

    @Mock
    private DataBase dataBase;
    @InjectMocks
    private SeeYourAccountService service;

    @Test
    public void testSeeAccount() {
        SeeYourAccountRequest request = new SeeYourAccountRequest("000-001");
        Optional<BankAccount> bankAccount = Optional.of(new BankAccount(
                "Example", "ExampleTwo","password", Roles.Regular_user, "000-111"
        ));
        bankAccount.get().setAccount(new Account(1L, 500));
        when(dataBase.seeYourAccount("000-001")).thenReturn(bankAccount);
        SeeYourAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).seeYourAccount("000-001");
    }
}
