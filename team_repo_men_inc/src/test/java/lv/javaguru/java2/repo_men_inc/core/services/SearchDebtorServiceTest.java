package lv.javaguru.java2.repo_men_inc.core.services;

import lv.javaguru.java2.repo_men_inc.core.domain.Item;
import lv.javaguru.java2.repo_men_inc.core.requests.*;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.responses.SearchDebtorResponse;
import lv.javaguru.java2.repo_men_inc.core.validators.SearchDebtorValidator;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchDebtorServiceTest {

    @Mock
    private Database database;
    @Mock
    private SearchDebtorValidator searchDebtorValidator;
    @InjectMocks
    private SearchDebtorService searchDebtorService;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(searchDebtorService, "orderingEnabled", true);
        ReflectionTestUtils.setField(searchDebtorService, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnErrorsWhenValidationFails() {
        SearchDebtorRequest invalidSearchDebtorRequest = new SearchDebtorRequest(null, null, null, null);
        when(searchDebtorValidator.validate(invalidSearchDebtorRequest)).thenReturn(List.of(new CoreError("Debtor name and List item", "cannot both be empty!")));
        SearchDebtorResponse searchDebtorResponse = searchDebtorService.execute(invalidSearchDebtorRequest);

        assertTrue(searchDebtorResponse.hasErrors());
    }

    @Test
    public void shouldReturnNoErrorsWhenValidationPasses() {
        SearchDebtorRequest validSearchDebtorRequest = new SearchDebtorRequest("name", null, null, null);
        when(searchDebtorValidator.validate(validSearchDebtorRequest)).thenReturn(List.of());
        SearchDebtorResponse searchDebtorResponse = searchDebtorService.execute(validSearchDebtorRequest);

        assertFalse(searchDebtorResponse.hasErrors());
    }

    @Test
    public void shouldNotInvokeDatabaseWhenValidationFails() {
        SearchDebtorRequest invalidSearchDebtorRequest = new SearchDebtorRequest(null, null, null, null);
        when(searchDebtorValidator.validate(invalidSearchDebtorRequest)).thenReturn(List.of(new CoreError("Debtor name and List item", "cannot both be empty!")));
        searchDebtorService.execute(invalidSearchDebtorRequest);

        verify(searchDebtorValidator).validate(invalidSearchDebtorRequest);
        verify(searchDebtorValidator).validate(any());
        verifyNoInteractions(database);
    }

    @Test
    public void shouldFindDebtorByName () {
        SearchDebtorRequest validSearchDebtorRequest = new SearchDebtorRequest("name", null, null, null);
        when(searchDebtorValidator.validate(any())).thenReturn(List.of());
        when(database.getByName("name")).thenReturn(new Debtor("name"));
        SearchDebtorResponse searchDebtorResponse = searchDebtorService.execute(validSearchDebtorRequest);
        assertEquals(1, searchDebtorResponse.getDebtors().size());
        assertEquals("name", searchDebtorResponse.getDebtors().get(0).getName());
    }

    @Test
    public void shouldFindDebtorByListItem () {
        SearchDebtorRequest validSearchDebtorRequest = new SearchDebtorRequest(null, "item", null, null);
        when(searchDebtorValidator.validate(any())).thenReturn(List.of());
        when(database.getByListItem("item")).thenReturn(List.of(new Debtor("name")));
        SearchDebtorResponse searchDebtorResponse = searchDebtorService.execute(validSearchDebtorRequest);
        assertEquals(1, searchDebtorResponse.getDebtors().size());
        assertEquals("name", searchDebtorResponse.getDebtors().get(0).getName());
    }

    @Test
    public void shouldFindDebtorByNAmeAndListItem () {
        SearchDebtorRequest validSearchDebtorRequest = new SearchDebtorRequest("name", "item", null, null);
        when(searchDebtorValidator.validate(any())).thenReturn(List.of());
        when(database.getByNameAndListItem("name", "item")).thenReturn(new Debtor("name"));
        SearchDebtorResponse searchDebtorResponse = searchDebtorService.execute(validSearchDebtorRequest);
        System.out.println(searchDebtorResponse.getDebtors());
        assertEquals(1, searchDebtorResponse.getDebtors().size());
        assertEquals("name", searchDebtorResponse.getDebtors().get(0).getName());
    }

//    @Test
//    public void shouldSearchByListItemWithOrderingAscending() {
//        Ordering ordering = new Ordering(OrderingType.LIST_ITEM_SIZE, OrderingDirection.ASC);
//        SearchDebtorRequest validSearchDebtorRequest = new SearchDebtorRequest(null, "item", ordering, null);
//        when(searchDebtorValidator.validate(validSearchDebtorRequest)).thenReturn(List.of());
//
//        List<Debtor> debtors = new ArrayList<>();
//        Debtor debtorOne = new Debtor("name1");
//        Debtor debtorTwo = new Debtor("name2");
//        Item itemZero = new Item("item 0");
//        Item itemOne = new Item("item 1");
//        debtorOne.getList().add(itemZero);
//        debtorTwo.getList().add(itemZero);
//        debtorTwo.getList().add(itemOne);
//        debtors.add(debtorOne);
//        debtors.add(debtorTwo);
//        when(database.getByListItem("item")).thenReturn(debtors);
//
//        SearchDebtorResponse searchDebtorResponse = searchDebtorService.execute(validSearchDebtorRequest);
//        assertFalse(searchDebtorResponse.hasErrors());
//        assertEquals(searchDebtorResponse.getDebtors().size(), 2);
//        assertEquals(1, searchDebtorResponse.getDebtors().get(0).getList().size());
//        assertEquals(2, searchDebtorResponse.getDebtors().get(1).getList().size());
//    }

//    @Test
//    public void shouldSearchByListItemWithOrderingDescending() {
//        Ordering ordering = new Ordering(OrderingType.LIST_ITEM_SIZE, OrderingDirection.DESC);
//        SearchDebtorRequest validSearchDebtorRequest = new SearchDebtorRequest(null, "item", ordering, null);
//        when(searchDebtorValidator.validate(validSearchDebtorRequest)).thenReturn(List.of());
//
//        List<Debtor> debtors = new ArrayList<>();
//        Debtor debtorOne = new Debtor("name1");
//        Debtor debtorTwo = new Debtor("name2");
//        Item itemZero = new Item("item 0");
//        Item itemOne = new Item("item 1");
//        debtorOne.getList().add(itemZero);
//        debtorTwo.getList().add(itemZero);
//        debtorTwo.getList().add(itemOne);
//        debtors.add(debtorOne);
//        debtors.add(debtorTwo);
//        when(database.getByListItem("item")).thenReturn(debtors);
//
//        SearchDebtorResponse searchDebtorResponse = searchDebtorService.execute(validSearchDebtorRequest);
//        assertFalse(searchDebtorResponse.hasErrors());
//        assertEquals(searchDebtorResponse.getDebtors().size(), 2);
//        assertEquals(2, searchDebtorResponse.getDebtors().get(0).getList().size());
//        assertEquals(1, searchDebtorResponse.getDebtors().get(1).getList().size());
//    }

    @Test
    public void shouldSearchByListItemWithPagingFirstPage() {
        Paging paging = new Paging(1, 1);
        SearchDebtorRequest validSearchDebtorRequest = new SearchDebtorRequest(null, "item", null, paging);
        when(searchDebtorValidator.validate(validSearchDebtorRequest)).thenReturn(List.of());

        List<Debtor> debtors = new ArrayList<>();
        Debtor debtorOne = new Debtor("name1");
        Debtor debtorTwo = new Debtor("name2");
        debtors.add(debtorOne);
        debtors.add(debtorTwo);
        when(database.getByListItem("item")).thenReturn(debtors);

        SearchDebtorResponse searchDebtorResponse = searchDebtorService.execute(validSearchDebtorRequest);
        assertFalse(searchDebtorResponse.hasErrors());
        assertEquals(searchDebtorResponse.getDebtors().size(), 1);
        assertEquals(searchDebtorResponse.getDebtors().get(0).getName(), "name1");
    }

    @Test
    public void shouldSearchByListItemWithPagingSecondPage() {
        Paging paging = new Paging(2, 1);
        SearchDebtorRequest validSearchDebtorRequest = new SearchDebtorRequest(null, "item", null, paging);
        when(searchDebtorValidator.validate(validSearchDebtorRequest)).thenReturn(List.of());

        List<Debtor> debtors = new ArrayList<>();
        Debtor debtorOne = new Debtor("name1");
        Debtor debtorTwo = new Debtor("name2");
        debtors.add(debtorOne);
        debtors.add(debtorTwo);
        when(database.getByListItem("item")).thenReturn(debtors);

        SearchDebtorResponse searchDebtorResponse = searchDebtorService.execute(validSearchDebtorRequest);
        assertFalse(searchDebtorResponse.hasErrors());
        assertEquals(searchDebtorResponse.getDebtors().size(), 1);
        assertEquals(searchDebtorResponse.getDebtors().get(0).getName(), "name2");
    }
}