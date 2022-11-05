package myApp.core.services;


import myApp.core.database.BankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GetAllBankAccountsServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;
    @InjectMocks
    private GetAllBankAccountsService service;

    @Test
   public void execute() {
        GetAllBankAccountsRequest request = new GetAllBankAccountsRequest();
        when(bankAccountRepository.getAllBankAccounts()).thenReturn(List.of(new BankAccount("Example", "Example",
                "Roles.Regular_user", "000000-00001")));
        GetAllBankAccountsResponse response = service.execute(request);
        assertEquals(response.getBankAccounts().get(0).getName(), "Example");
        assertEquals(response.getBankAccounts().get(0).getSurname(), "Example");
        assertEquals(response.getBankAccounts().get(0).getPersonalCode(), "000000-00001");
        verify(bankAccountRepository).getAllBankAccounts();
    }
}


