package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.domain.Recipe;
import lv.javaguru.java2.cookingApp.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.requests.PrintRecipeToConsoleRequest;
import lv.javaguru.java2.cookingApp.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.responses.CoreError;
import lv.javaguru.java2.cookingApp.responses.PrintRecipeToConsoleResponse;
import lv.javaguru.java2.cookingApp.services.validators.DeleteRecipeRequestValidator;
import lv.javaguru.java2.cookingApp.services.validators.PrintRecipeToConsoleValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PrintRecipeToConsoleServiceTest {

    @Mock private Database database;
    @Mock private PrintRecipeToConsoleValidator validator;

    @InjectMocks
    private PrintRecipeToConsoleService service;

    @Test
    void testShouldReturnResponseWithErrorWhenRequestIsNotValid() {
        PrintRecipeToConsoleRequest request = Mockito.mock(PrintRecipeToConsoleRequest.class);
        CoreError error = new CoreError("Test", "Test");
        Mockito.when(validator.validate(request)).thenReturn(List.of(error));
        PrintRecipeToConsoleResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Test", error.getField());
        assertEquals("Test", error.getMessage());
    }

    @Test
    void testShouldReturnResponseWithRecipe() {
        PrintRecipeToConsoleRequest request = Mockito.mock(PrintRecipeToConsoleRequest.class);
        Recipe recipe = Mockito.mock(Recipe.class);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.getById(request.getId())).thenReturn(recipe);
        PrintRecipeToConsoleResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertNotNull(response.getPrintedRecipe());
        Mockito.verify(database).getById(request.getId());
        Mockito.verify(recipe).printToConsole();
    }

}