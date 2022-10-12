package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.core.requests.AddHarvestedItemRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddHarvestedItemResponse;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.validators.AddHarvestedItemValidator;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.domain.Debtor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddHarvestedItemServiceTest {

    @Mock
    private Database database;
    @Mock
    private AddHarvestedItemValidator addHarvestedItemValidator;
    @InjectMocks
    private AddHarvestedItemService addHarvestedItemService;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddHarvestedItemRequest invalidAddHarvestedItemRequest = new AddHarvestedItemRequest(1L, null);
        when(addHarvestedItemValidator.validate(invalidAddHarvestedItemRequest)).thenReturn(List.of(new CoreError("item", "cannot be empty!")));
        AddHarvestedItemResponse addHarvestedItemResponse = addHarvestedItemService.execute(invalidAddHarvestedItemRequest);
        assertTrue(addHarvestedItemResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithoutErrorsWhenValidationPasses() {
        AddHarvestedItemRequest validAddHarvestedItemRequest = new AddHarvestedItemRequest(1L, "null");
        when(addHarvestedItemValidator.validate(validAddHarvestedItemRequest)).thenReturn(List.of());
        when(database.getById(validAddHarvestedItemRequest.getDebtorsId())).thenReturn(new Debtor("name"));
        AddHarvestedItemResponse addHarvestedItemResponse = addHarvestedItemService.execute(validAddHarvestedItemRequest);
        assertFalse(addHarvestedItemResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrorsFromValidatorWhenValidationFails() {
        AddHarvestedItemRequest invalidAddHarvestedItemRequest = new AddHarvestedItemRequest(1L, null);
        when(addHarvestedItemValidator.validate(invalidAddHarvestedItemRequest)).thenReturn(List.of(new CoreError("item", "cannot be empty!")));
        AddHarvestedItemResponse addHarvestedItemResponse = addHarvestedItemService.execute(invalidAddHarvestedItemRequest);
        assertEquals(1, addHarvestedItemResponse.getErrors().size());
        assertEquals("item", addHarvestedItemResponse.getErrors().get(0).getField());
        assertEquals("cannot be empty!", addHarvestedItemResponse.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldNotInvokeDatabaseWhenValidationFails() {
        AddHarvestedItemRequest invalidAddHarvestedItemRequest = new AddHarvestedItemRequest(1L, null);
        when(addHarvestedItemValidator.validate(invalidAddHarvestedItemRequest)).thenReturn(List.of(new CoreError("item", "cannot be empty!")));
        addHarvestedItemService.execute(invalidAddHarvestedItemRequest);
        verifyNoInteractions(database);
    }

    @Test
    public void shouldAddItemToDebtorInDatabaseWhenValidationPasses() {
        AddHarvestedItemRequest validAddHarvestedItemRequest = new AddHarvestedItemRequest(1L, "null");
        when(addHarvestedItemValidator.validate(any())).thenReturn(List.of());
        when(database.getById(1L)).thenReturn(new Debtor("name"));
        AddHarvestedItemResponse addHarvestedItemResponse = addHarvestedItemService.execute(validAddHarvestedItemRequest);
        assertFalse(addHarvestedItemResponse.hasErrors());
        assertTrue(addHarvestedItemResponse.isHarvestedItemAdded());
    }
}