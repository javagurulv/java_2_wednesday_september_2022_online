package myApp.acceptanceTests;

import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import myApp.core.services.AddBankAccountService;
import myApp.core.services.GetAllBankAccountsService;
import myApp.dependency_injection.ApplicationContext;
import myApp.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest1 {
    private ApplicationContext appContext =
            new DIApplicationContextBuilder().build("myApp");

    @Test
    public void shouldReturnCorrectBookList() {
        AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "ExampleTwo",
                "000000-00001", "password");
        getAddBankAccountService().execute(bankAccountOne);
        AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "",
                "0", "password");
        getAddBankAccountService().execute(bankAccountTwo);
        GetAllBankAccountsResponse response = getAllBankAccountsService().execute(new GetAllBankAccountsRequest());
        assertEquals(1,response.getBankAccounts().size());
    }

    private AddBankAccountService getAddBankAccountService() {
        return appContext.getBean(AddBankAccountService.class);
    }

    private GetAllBankAccountsService getAllBankAccountsService() {
        return appContext.getBean(GetAllBankAccountsService.class);
    }

}
