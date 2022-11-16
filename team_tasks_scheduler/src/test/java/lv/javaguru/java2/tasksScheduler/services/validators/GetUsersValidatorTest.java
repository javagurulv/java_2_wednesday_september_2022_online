package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.requests.GetUsersRequest;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GetUsersValidatorTest {

//    private GetUsersValidator validator = new GetUsersValidator();
//
//    @Test
//    public void shouldReturnErrorWhenOrderingFromAdminByNull() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering(null, "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.ADMIN);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order By");
//        assertEquals(errors.get(0).getMessage(), "Must contain 'username' or 'email' only!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenOrderingFromAdminBySpaces() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering(" ", "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.ADMIN);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order By");
//        assertEquals(errors.get(0).getMessage(), "Must contain 'username' or 'email' only!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenOrderingFromAdminByWrongField() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("wrong", "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.ADMIN);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order By");
//        assertEquals(errors.get(0).getMessage(), "Must contain 'username' or 'email' only!");
//    }
//
//    @Test
//    public void shouldSuccessWhenOrderingFromAdminByUsername() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("username", "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.ADMIN);
//        assertEquals(errors.size(), 0);
//    }
//
//    @Test
//    public void shouldSuccessWhenOrderingFromAdminByEmail() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("email", "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.ADMIN);
//        assertEquals(errors.size(), 0);
//    }
//
//    @Test
//    public void shouldReturnErrorWhenOrderingByNull() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering(null, "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order By");
//        assertEquals(errors.get(0).getMessage(), "Must contain 'username' only!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenOrderingBySpaces() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering(" ", "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order By");
//        assertEquals(errors.get(0).getMessage(), "Must contain 'username' only!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenOrderingFromStartByWrongField() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("wrong", "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order By");
//        assertEquals(errors.get(0).getMessage(), "Must contain 'username' only!");
//    }
//
//    @Test
//    public void shouldSuccessWhenOrderingFromStartByUsername() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("username", "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 0);
//    }
//
//    @Test
//    public void shouldReturnErrorWhenNullOrderDirection() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("email", null));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order Direction");
//        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenEmptyOrderDirection() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("email", " "));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order Direction");
//        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenWrongOrderDirection() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("email", "WRONG"));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order Direction");
//        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
//    }
//
//    @Test
//    public void shouldSuccessWhenAscOrderDirection() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("email", "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 0);
//    }
//
//    @Test
//    public void shouldSuccessWhenDescOrderDirection() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("email", "DESCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 0);
//    }
//
//    @Test
//    public void shouldReturnErrorWhenOrderDirectionButOrderByNull() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering(null, "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order By");
//        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenOrderDirectionButOrderByEmpty() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering(" ", "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order By");
//        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
//    }
//
//    @Test
//    public void shouldSuccessWhenOrderDirectionAndOrderByProvided() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("username", "ASCENDING"));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 0);
//    }
//
//    @Test
//    public void shouldReturnErrorWhenOrderByButOrderDirectionNull() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("username", null));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order Direction");
//        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenOrderByButOrderDirectionEmpty() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Ordering("username", " "));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Order Direction");
//        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenPageNumberIsZero() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Paging(0, 1));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Page Number");
//        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenPageNumberIsLessThanZero() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Paging(-1, 1));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Page Number");
//        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
//    }
//
//    @Test
//    public void shouldSuccessWhenPageNumberIsValid() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Paging(1, 1));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 0);
//    }
//
//    @Test
//    public void shouldReturnErrorWhenPageSizeIsZero() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Paging(1, 0));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Page Size");
//        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenPageSizeIsLessThanZero() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Paging(1, -1));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Page Size");
//        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
//    }
//
//    @Test
//    public void shouldSuccessWhenPageSizeIsValid() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Paging(1, 1));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 0);
//    }
//
//    @Test
//    public void shouldReturnErrorWhenPageSizeButPageNumberIsZero() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Paging(0, 1));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Page Number");
//        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
//    }
//
//    @Test
//    public void shouldReturnErrorWhenPageNumberButPageSizeIsZero() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Paging(1, 0));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 1);
//        assertEquals(errors.get(0).getField(), "Page Size");
//        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
//    }
//
//    @Test
//    public void shouldSuccessWhenPageSizeAndPageNumberAreValid() {
//        GetUsersRequest request = new GetUsersRequest("test", "test@test.com",
//                new Paging(1, 1));
//        List<CoreError> errors = validator.validate(request, MenuType.START);
//        assertEquals(errors.size(), 0);
//    }


}