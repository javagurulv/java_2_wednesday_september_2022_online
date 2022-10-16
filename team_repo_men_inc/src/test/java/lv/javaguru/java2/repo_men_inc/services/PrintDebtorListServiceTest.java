package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.core.requests.PrintDebtorsListRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.PrintDebtorsListResponse;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.domain.Debtor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrintDebtorListServiceTest {

    @Mock
    private Database database;
    @InjectMocks
    private PrintDebtorListService printDebtorListService;

    @Test
    public void shouldReturnDebtorsFromDatabase () {
        List<Debtor> debtors = new ArrayList<>();
        debtors.add(new Debtor("name"));
        when(database.getAllDebtors()).thenReturn(debtors);
        PrintDebtorsListRequest printDebtorsListRequest = new PrintDebtorsListRequest();
        PrintDebtorsListResponse printDebtorsListResponse = printDebtorListService.execute(printDebtorsListRequest);
        assertEquals(1, printDebtorsListResponse.getDebtors().size());
        assertEquals("name", printDebtorsListResponse.getDebtors().get(0).getName());
    }
}
