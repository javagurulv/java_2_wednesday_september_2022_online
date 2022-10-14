package myApp.acceptanceTests;

import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.Ordering;
import myApp.core.requests.Paging;
import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.SearchBankAccountResponse;
import myApp.core.services.AddBankAccountService;
import myApp.core.services.SearchBankAccountService;
import myApp.dependency_injection.ApplicationContext;
import myApp.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AcceptanceTest2 {

    private ApplicationContext appContext =
            new DIApplicationContextBuilder().build("myApp");

    @Test
    public void testSuccessFindTwoBankAccounts() {
        AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "ExampleOne",
                "000000-00002", "password");
        getAddBankAccountService().execute(bankAccountOne);

        AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "ExampleTwo",
                "000000-00003", "password");
        getAddBankAccountService().execute(bankAccountTwo);

        SearchBankAccountRequest searchRequest = new SearchBankAccountRequest("Example", null,null );
        SearchBankAccountResponse response = getSearchBankAccountService().execute(searchRequest);
        assertEquals(2, response.getBankAccounts().size());
        assertEquals(response.getBankAccounts().get(0).getName(), "Example");
        assertEquals(response.getBankAccounts().get(0).getSurname(), "ExampleOne");
        assertEquals(response.getBankAccounts().get(1).getName(), "Example");
        assertEquals(response.getBankAccounts().get(1).getSurname(), "ExampleTwo");
    }

    @Test
    public void testSearchBooksWhenOrderingIsDescending() {
        AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "A",
                "000000-00002", "password");
        getAddBankAccountService().execute(bankAccountOne);

        AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "B",
                "000000-00003", "password");
        getAddBankAccountService().execute(bankAccountTwo);

        Ordering ordering = new Ordering("surname", "DESCENDING");
        SearchBankAccountRequest searchRequest = new SearchBankAccountRequest("Example", null,null,
                ordering);
        SearchBankAccountResponse response = getSearchBankAccountService().execute(searchRequest);
        assertEquals(2, response.getBankAccounts().size());
        assertEquals(response.getBankAccounts().get(0).getName(), "Example");
        assertEquals(response.getBankAccounts().get(0).getSurname(), "B");
        assertEquals(response.getBankAccounts().get(1).getName(), "Example");
        assertEquals(response.getBankAccounts().get(1).getSurname(), "A");
    }

    @Test
    public void testSearchBooksWhenOrderingIsAscending() {
        AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "A",
                "000000-00002", "password");
        getAddBankAccountService().execute(bankAccountOne);

        AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "B",
                "000000-00003", "password");
        getAddBankAccountService().execute(bankAccountTwo);

        Ordering ordering = new Ordering("surname", "ASCENDING");
        SearchBankAccountRequest searchRequest = new SearchBankAccountRequest("Example", null,null,
                ordering);
        SearchBankAccountResponse response = getSearchBankAccountService().execute(searchRequest);

        assertEquals(2, response.getBankAccounts().size());
        assertEquals(response.getBankAccounts().get(0).getName(), "Example");
        assertEquals(response.getBankAccounts().get(0).getSurname(), "A");
        assertEquals(response.getBankAccounts().get(1).getName(), "Example");
        assertEquals(response.getBankAccounts().get(1).getSurname(), "B");
    }

    @Test
    public void testSearchBooksWithOrderingPaging() {
        AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "A",
                "000000-00002", "password");
        getAddBankAccountService().execute(bankAccountOne);

        AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "B",
                "000000-00003", "password");
        getAddBankAccountService().execute(bankAccountTwo);

        Ordering ordering = new Ordering("surname", "ASCENDING");
        Paging paging = new Paging(1, 1);
        SearchBankAccountRequest searchRequest = new SearchBankAccountRequest("Example", null,null,
                ordering, paging);
        SearchBankAccountResponse response = getSearchBankAccountService().execute(searchRequest);

        assertEquals(1, response.getBankAccounts().size());
        assertEquals(response.getBankAccounts().get(0).getName(), "Example");
        assertEquals(response.getBankAccounts().get(0).getSurname(), "A");
    }

    private AddBankAccountService getAddBankAccountService() {
        return appContext.getBean(AddBankAccountService.class);
    }

    private SearchBankAccountService getSearchBankAccountService() {
        return appContext.getBean(SearchBankAccountService.class);
    }

}
