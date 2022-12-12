package lv.javaguru.java2.repo_men_inc.acceptance_tests;

import lv.javaguru.java2.repo_men_inc.config.RepoMenIncConfiguration;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import lv.javaguru.java2.repo_men_inc.core.requests.*;
import lv.javaguru.java2.repo_men_inc.core.responses.*;
import lv.javaguru.java2.repo_men_inc.core.services.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepoMenIncConfiguration.class})
public class AcceptanceTest1 extends AcceptanceTestBase{

    @Test
    public void addNewDebtorToDatabase() {
        AddDebtorRequest addDebtorRequest1 = new AddDebtorRequest(debtorNotPresentInDatabase);
        AddDebtorResponse addDebtorResponse1 = getAddDebtorService().execute(addDebtorRequest1);

        AddDebtorRequest addDebtorRequest2 = new AddDebtorRequest(debtorPresentInDatabase);
        AddDebtorResponse addDebtorResponse2 = getAddDebtorService().execute(addDebtorRequest2);

        assertTrue(addDebtorResponse1.isDebtorSavedToDatabase());
        assertFalse(addDebtorResponse2.isDebtorSavedToDatabase());
    }

    @Test
    public void addHarvestedItemToDebtorsItemsList() {
        AddHarvestedItemRequest addHarvestedItemRequest1 = new AddHarvestedItemRequest(idOfTheFirstDebtorInTheDatabase, itemNotYetPresentInTheItemsListOfTheFirstDebtorInTheDatabase);
        AddHarvestedItemResponse addHarvestedItemResponse1 = getAddHarvestedItemService().execute(addHarvestedItemRequest1);

        AddHarvestedItemRequest addHarvestedItemRequest2 = new AddHarvestedItemRequest(idOfTheFirstDebtorInTheDatabase, itemAlreadyPresentInTheItemsListOfTheFirstDebtorInTheDatabase);
        AddHarvestedItemResponse addHarvestedItemResponse2 = getAddHarvestedItemService().execute(addHarvestedItemRequest2);

        assertTrue(addHarvestedItemResponse1.isHarvestedItemAdded());
        assertFalse(addHarvestedItemResponse2.isHarvestedItemAdded());
    }

    @Test
    public void printDebtorList() {
        PrintDebtorsListRequest printDebtorsListRequest = new PrintDebtorsListRequest();
        PrintDebtorsListResponse printDebtorsListResponse = getPrintDebtorListService().execute(printDebtorsListRequest);
        assertEquals(3, printDebtorsListResponse.getDebtors().size());
    }

    @Test
    public void removeExistingDebtorFromDatabase() {
        RemoveDebtorRequest removeDebtorRequest = new RemoveDebtorRequest(idOfTheFirstDebtorInTheDatabase);
        RemoveDebtorResponse removeDebtorResponse = getRemoveDebtorService().execute(removeDebtorRequest);
        assertTrue(removeDebtorResponse.isDebtorRemoved());
    }

    @Test
    public void removeNonExistentDebtorFromDatabase() {
        RemoveDebtorRequest removeDebtorRequest = new RemoveDebtorRequest(nonExistentDebtorsId);
        RemoveDebtorResponse removeDebtorResponse = getRemoveDebtorService().execute(removeDebtorRequest);
        assertFalse(removeDebtorResponse.isDebtorRemoved());
    }

    @Test
    public void searchForAnExistingDebtorsInDatabase() {
        System.out.println(appContext.getBean(Database.class).getAllDebtors());
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(debtorPresentInDatabase, null, null, null);
        SearchDebtorResponse searchDebtorResponse = getSearchDebtorService().execute(searchDebtorRequest);
        assertEquals(1, searchDebtorResponse.getDebtors().size());
        assertEquals(debtorPresentInDatabase, searchDebtorResponse.getDebtors().get(0).getName());
    }

    @Test
    public void searchForAnNonExistingDebtorInTheDatabase() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(debtorNotPresentInDatabase, null, null, null);
        SearchDebtorResponse searchDebtorResponse = getSearchDebtorService().execute(searchDebtorRequest);
        assertNull(searchDebtorResponse.getDebtors().get(0));
    }

    @Test
    public void searchByAnExistingItemItemName() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(null, itemPresentInSeveralDebtorsLists, null, null);
        SearchDebtorResponse searchDebtorResponse = getSearchDebtorService().execute(searchDebtorRequest);
        assertEquals(2, searchDebtorResponse.getDebtors().size());
        assertEquals("mr x", searchDebtorResponse.getDebtors().get(0).getName());
        assertEquals("mr y", searchDebtorResponse.getDebtors().get(1).getName());
    }

    @Test
    public void searchByAnNonExistingItemItemName() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(null, itemNotPresentInAnyDebtorsLists, null, null);
        SearchDebtorResponse searchDebtorResponse = getSearchDebtorService().execute(searchDebtorRequest);
        assertTrue(searchDebtorResponse.getDebtors().isEmpty());
    }

    @Test
    public void searchWithOrderingAscending() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(null, itemPresentInSeveralDebtorsLists, orderByListSizeAscending, null);
        SearchDebtorResponse searchDebtorResponse = getSearchDebtorService().execute(searchDebtorRequest);
        assertEquals(2, searchDebtorResponse.getDebtors().size());
        assertEquals("mr x", searchDebtorResponse.getDebtors().get(0).getName());
        assertEquals("mr y", searchDebtorResponse.getDebtors().get(1).getName());
    }

    @Test
    public void searchWithOrderingDescending() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(null, itemPresentInSeveralDebtorsLists, orderByNameDescending, null);
        SearchDebtorResponse searchDebtorResponse = getSearchDebtorService().execute(searchDebtorRequest);
        assertEquals(2, searchDebtorResponse.getDebtors().size());
        assertEquals("mr y", searchDebtorResponse.getDebtors().get(0).getName());
        assertEquals("mr x", searchDebtorResponse.getDebtors().get(1).getName());
    }

    @Test
    public void searchWithPagingFirstPage() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(null, itemPresentInSeveralDebtorsLists, null, firstPage);
        SearchDebtorResponse searchDebtorResponse = getSearchDebtorService().execute(searchDebtorRequest);
        assertEquals(1, searchDebtorResponse.getDebtors().size());
        assertEquals("mr x", searchDebtorResponse.getDebtors().get(0).getName());
    }

    @Test
    public void searchWithPagingSecondPage() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(null, itemPresentInSeveralDebtorsLists, null, secondPage);
        SearchDebtorResponse searchDebtorResponse = getSearchDebtorService().execute(searchDebtorRequest);
        assertEquals(1, searchDebtorResponse.getDebtors().size());
        assertEquals("mr y", searchDebtorResponse.getDebtors().get(0).getName());
    }

    private AddDebtorService getAddDebtorService() {
        return appContext.getBean(AddDebtorService.class);
    }

    private AddHarvestedItemService getAddHarvestedItemService() {
        return appContext.getBean(AddHarvestedItemService.class);
    }

    private RemoveDebtorService getRemoveDebtorService() {
        return appContext.getBean(RemoveDebtorService.class);
    }

    private PrintDebtorListService getPrintDebtorListService() {
        return appContext.getBean(PrintDebtorListService.class);
    }

    private SearchDebtorService getSearchDebtorService() {
        return appContext.getBean(SearchDebtorService.class);
    }
}
