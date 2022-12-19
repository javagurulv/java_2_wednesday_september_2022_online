package lv.javaguru.java2.repo_men_inc.core.services;

import lv.javaguru.java2.repo_men_inc.core.requests.RemoveDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.responses.RemoveDebtorResponse;
import lv.javaguru.java2.repo_men_inc.core.validators.RemoveDebtorValidator;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveDebtorServiceTest {

    @Mock
    private Database database;
    @Mock
    private RemoveDebtorValidator removeDebtorValidator;
    @InjectMocks
    private RemoveDebtorService removeDebtorService;

    @Test
    public void shouldReturnErrorIfRequestIsInvalid() {
        RemoveDebtorRequest invalidRemoveDebtorRequest = new RemoveDebtorRequest(null);
        when(removeDebtorValidator.validate(invalidRemoveDebtorRequest)).thenReturn(List.of(new CoreError("Id", "cannot be empty!")));
        RemoveDebtorResponse removeDebtorResponse = removeDebtorService.execute(invalidRemoveDebtorRequest);
        assertTrue(removeDebtorResponse.hasErrors());
        assertEquals(1, removeDebtorResponse.getErrors().size());
        assertEquals("Id", removeDebtorResponse.getErrors().get(0).getField());
        assertEquals("cannot be empty!", removeDebtorResponse.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldDeleteDebtorFromDatabaseIfRequestIsValid () {
        when(removeDebtorValidator.validate(any())).thenReturn(List.of());
        when(database.deleteById(1L)).thenReturn(true);
        RemoveDebtorRequest validRemoveDebtorRequest = new RemoveDebtorRequest(1L);
        RemoveDebtorResponse removeDebtorResponse = removeDebtorService.execute(validRemoveDebtorRequest);
        assertFalse(removeDebtorResponse.hasErrors());
        assertTrue(removeDebtorResponse.isDebtorRemoved());
    }


}