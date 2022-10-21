package myApp.core.services.validators;
/*
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class AddBankAccountValidatorTest {


    AddBankAccountValidator validator = new AddBankAccountValidator();

    @Test
    public void testShouldReturnErrorAboutName() {
        AddBankAccountRequest request = new AddBankAccountRequest("","ExampleTwo",
                "000-001"
                ,"password");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Name", errors.get(0).getField());
    }

    @Test
    public void testShouldReturnErrorAboutSurname() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example","",
                "000-001"
                ,"password");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Surname", errors.get(0).getField());
    }

    @Test
    public void testShouldReturnErrorAboutPersonalCode() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example","ExampleTwo",
                "000-001"
                ,"password");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Personal Code", errors.get(0).getField());
    }

    @Test
    public void testShouldReturnErrorAboutNameAndSurname() {
        AddBankAccountRequest request = new AddBankAccountRequest("","",
                "000000-00001"
                ,"password");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Name", errors.get(0).getField());
        assertEquals("Field: Surname", errors.get(1).getField());
    }

    @Test
    public void testShouldReturnErrorAboutNameAndSurnameAndPersonalCode() {
        AddBankAccountRequest request = new AddBankAccountRequest("","",
                ""
                ,"password");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Name", errors.get(0).getField());
        assertEquals("Field: Surname", errors.get(1).getField());
        assertEquals("Field: Personal Code", errors.get(2).getField());
    }

    @Test
    public void testShouldReturnErrorAboutAllFields() {
        AddBankAccountRequest request = new AddBankAccountRequest("","",
                ""
                ,"");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Name", errors.get(0).getField());
        assertEquals("Field: Surname", errors.get(1).getField());
        assertEquals("Field: Personal Code", errors.get(2).getField());
        assertEquals("Field: Password", errors.get(3).getField());
    }
}

 */