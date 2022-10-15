package myApp.core.services.validators;

import myApp.core.requests.Ordering;
import myApp.core.requests.Paging;
import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchBankAccountValidatorTest {

    @Mock
    private SearchBankAccountRequestFieldValidator fieldValidator;
    @Mock
    private OrderingValidator orderingValidator;
    @Mock
    private PagingValidator pagingValidator;
    @InjectMocks
    private SearchBankAccountValidator validator;

    @Test
    public void testSuccessFindBankAccountWithoutPaging() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav","Kulikov",
                "000000-00001");
        when(fieldValidator.validate(request)).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
        verifyNoMoreInteractions(pagingValidator);
    }

    @Test
    public void testShouldReturnErrorAboutName() {
        SearchBankAccountRequest request = new SearchBankAccountRequest(null,"Kulikov",
                "000000-00001");
        when(fieldValidator.validate(request)).thenReturn(List.of(new CoreError("Name","Name may contains only letters " +
                "and cannot be empty")));
        List<CoreError> errors = validator.validate(request);
        assertEquals("Name", errors.get(0).getField());
        assertEquals("Name may contains only letters " +
                "and cannot be empty", errors.get(0).getMessage());
    }

    @Test
    public void testShouldReturnErrorAboutSurname() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav",null,
                "000000-00001");
        when(fieldValidator.validate(request)).thenReturn(List.of(new CoreError("Surname","Surname may contains only letters " +
                "and cannot be empty" )));
        List<CoreError> errors = validator.validate(request);
        assertEquals("Surname", errors.get(0).getField());
        assertEquals("Surname may contains only letters " +
                "and cannot be empty", errors.get(0).getMessage());
        assertEquals(1, errors.size());
    }

    @Test
    public void testShouldReturnErrorAboutPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav","Kulikov",
                "01");
        when(fieldValidator.validate(request)).thenReturn(List.of(new CoreError("Personal code","Personal code may contains only numbers " +
                "and cannot be empty")));
        List<CoreError> errors = validator.validate(request);
        assertEquals("Personal code", errors.get(0).getField());
        assertEquals("Personal code may contains only numbers " +
                "and cannot be empty", errors.get(0).getMessage());
    }
    @Test
    public void testSuccessOrdering() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav","Kulikov",
                "0000000-00001", new Ordering("title", "ASCENDING"));
        when(orderingValidator.validateOrdering(request)).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void testShouldReturnErrorsWhenOrderDirectionWrong() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav","Kulikov",
                "0000000-00001", new Ordering("title", "ASCENDING"));
        when(orderingValidator.validateOrdering(request)).thenReturn(List.of(new CoreError("Order direction",
                "Order direction cannot be empty and can only contain a 'ASCENDING' or 'DESCENDING'")));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.get(0).getField(), "Order direction");
        assertEquals(errors.get(0).getMessage(),
                "Order direction cannot be empty and can only contain a 'ASCENDING' or 'DESCENDING'");
    }

    @Test
    public void testShouldBeNoOneInteractionOfOrdering() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav","Kulikov",
                "0000000-00001");
        validator.validate(request);
        verifyNoMoreInteractions(orderingValidator);
    }

    @Test
    public void testSuccessPaging() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav","Kulikov",
                "0000000-00001", new Paging(1,2));
        when(pagingValidator.validate(request.getPaging())).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void testShouldReturnErrorsWhenPageNumberIsNull() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Vladislav","Kulikov",
                "0000000-00001", new Paging(null, 2));
        CoreError error = new CoreError("pageNumber", "Must be greater then 0!");
        when(pagingValidator.validate(request.getPaging())).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }
}