package myApp.acceptanceTests;

import myApp.config.BankAccountConfiguration;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.AddUserRequest;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import myApp.core.services.AddBankAccountService;
import myApp.core.services.GetAllBankAccountsService;
import myApp.matcher.DatabaseCleaner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BankAccountConfiguration.class})
@Sql({"/schema.sql"})
public class AcceptanceTest1 {

    @Autowired
    private AddBankAccountService addService;
    @Autowired
    private GetAllBankAccountsService getAllBooksService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }
    @Test
    public void testShouldReturnCorrectBankAccountList() {
        AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "ExampleTwo",
                "000000-00001");
        addService.execute(bankAccountOne, new AddUserRequest("000000-00001", "password"));
        AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "",
                "0");
        addService.execute(bankAccountTwo,new AddUserRequest("0", "password"));
        GetAllBankAccountsResponse response = getAllBooksService.execute(new GetAllBankAccountsRequest());
        assertEquals(1,response.getBankAccounts().size());
    }
}


