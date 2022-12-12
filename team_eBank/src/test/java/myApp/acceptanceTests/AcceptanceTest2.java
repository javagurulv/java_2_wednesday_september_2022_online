package myApp.acceptanceTests;
/*
import myApp.config.SpringCoreConfiguration;
import myApp.core.requests.*;
import myApp.core.responses.SearchBankAccountResponse;
import myApp.core.services.AddBankAccountService;
import myApp.core.services.SearchBankAccountService;
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
@ContextConfiguration(classes = {SpringCoreConfiguration.class})
@Sql({"/schema.sql"})
public class AcceptanceTest2 {

    @Autowired
    private AddBankAccountService addService;
    @Autowired
    private SearchBankAccountService searchBankAccountServiceService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }
    @Test
    public void testSuccessFindTwoBankAccounts() {
        AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "ExampleOne",
                "000000-00002");
        addService.execute(bankAccountOne,new AddUserRequest("000000-00002","password" ));

        AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "ExampleTwo",
                "000000-00003");
        addService.execute(bankAccountTwo, new AddUserRequest("000000-00003","password"));

        SearchBankAccountRequest searchRequest = new SearchBankAccountRequest("Example", null,null );
        SearchBankAccountResponse response = searchBankAccountServiceService.execute(searchRequest);
        assertEquals(2, response.getBankAccounts().size());
        assertEquals(response.getBankAccounts().get(0).getName(), "Example");
        assertEquals(response.getBankAccounts().get(0).getSurname(), "ExampleOne");
        assertEquals(response.getBankAccounts().get(1).getName(), "Example");
        assertEquals(response.getBankAccounts().get(1).getSurname(), "ExampleTwo");
    }

    @Test
    public void testSearchBooksWhenOrderingIsDescending() {
        AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "A",
                "000000-00002");
        addService.execute(bankAccountOne, new AddUserRequest("000000-00002","password"));

        AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "B",
                "000000-00003");
        addService.execute(bankAccountTwo, new AddUserRequest("000000-00003","password"));

        Ordering ordering = new Ordering("surname", "DESCENDING");
        SearchBankAccountRequest searchRequest = new SearchBankAccountRequest("Example", null,null,
                ordering);
        SearchBankAccountResponse response = searchBankAccountServiceService.execute(searchRequest);
        assertEquals(2, response.getBankAccounts().size());
        assertEquals(response.getBankAccounts().get(0).getName(), "Example");
        assertEquals(response.getBankAccounts().get(0).getSurname(), "B");
        assertEquals(response.getBankAccounts().get(1).getName(), "Example");
        assertEquals(response.getBankAccounts().get(1).getSurname(), "A");
    }

    @Test
    public void testSearchBooksWhenOrderingIsAscending() {
        AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "A",
                "000000-00002");
        addService.execute(bankAccountOne, new AddUserRequest("000000-00002","password"));

        AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "B",
                "000000-00003");
        addService.execute(bankAccountTwo, new AddUserRequest("000000-00003","password"));

        Ordering ordering = new Ordering("surname", "ASCENDING");
        SearchBankAccountRequest searchRequest = new SearchBankAccountRequest("Example", null,null,
                ordering);
        SearchBankAccountResponse response = searchBankAccountServiceService.execute(searchRequest);

        assertEquals(2, response.getBankAccounts().size());
        assertEquals(response.getBankAccounts().get(0).getName(), "Example");
        assertEquals(response.getBankAccounts().get(0).getSurname(), "A");
        assertEquals(response.getBankAccounts().get(1).getName(), "Example");
        assertEquals(response.getBankAccounts().get(1).getSurname(), "B");
    }

    @Test
    public void testSearchBooksWithOrderingPaging() {
        AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "A",
                "000000-00002");
        addService.execute(bankAccountOne, new AddUserRequest("000000-00002","password"));

        AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "B",
                "000000-00003");
        addService.execute(bankAccountTwo, new AddUserRequest("000000-00003","password"));

        Ordering ordering = new Ordering("surname", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchBankAccountRequest searchRequest = new SearchBankAccountRequest("Example", null,null,
                ordering, paging);
        SearchBankAccountResponse response = searchBankAccountServiceService.execute(searchRequest);

        assertEquals(1, response.getBankAccounts().size());
        assertEquals(response.getBankAccounts().get(0).getName(), "Example");
        assertEquals(response.getBankAccounts().get(0).getSurname(), "A");
    }
}

 */
