package myApp.core.services.validators;

//TODO NEED FIX
/*
@Ignore
public class RemoveBankAccountValidatorTest {

    RemoveBankAccountValidator validator = new RemoveBankAccountValidator();

    @Test
    public void testSuccessValidate() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest("000000-00001");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testShouldReturnErrorAboutWrongPersonalCode() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest("000000-001");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Personal code", errors.get(0).getField());
        assertEquals("Personal code can only contain numbers and  must not be empty", errors.get(0).getMessage());
    }

    @Test
    public void testShouldReturnErrorAboutEmptyPersonalCode() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest("");
        List<CoreError> errors = validator.validate(request);
        assertEquals("Field: Personal code", errors.get(0).getField());
        assertEquals("Personal code can only contain numbers and  must not be empty", errors.get(0).getMessage());
    }
}


 */
