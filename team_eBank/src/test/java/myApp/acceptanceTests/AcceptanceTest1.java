package myApp.acceptanceTests;
/*
import myApp.config.BankAccountConfiguration;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import myApp.core.services.AddBankAccountService;
import myApp.core.services.GetAllBankAccountsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import static org.junit.Assert.assertEquals;

public class AcceptanceTest1 {
    private  ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(BankAccountConfiguration.class);
    }
    @Test
    public void testShouldReturnCorrectBankAccountList() {
        AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "ExampleTwo",
                "000000-00001");
        getAddBankAccountService().execute(bankAccountOne);
        AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "",
                "0");
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

 */


