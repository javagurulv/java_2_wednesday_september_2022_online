package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.PrintRecipeToConsoleRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.responses.PrintRecipeToConsoleResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.IdValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PrintRecipeToConsoleServiceTest {

    @Mock private RecipeRepository recipeRepository;
    @Mock private IdValidator validator;

    @InjectMocks
    private PrintRecipeToConsoleService service;

    @Test
    void testShouldReturnResponseWithErrorWhenRequestIsNotValid() {
        PrintRecipeToConsoleRequest request = Mockito.mock(PrintRecipeToConsoleRequest.class);
        CoreError error = new CoreError("Test", "Test");
        Mockito.when(validator.validate(request.getId())).thenReturn(List.of(error));
        PrintRecipeToConsoleResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Test", error.getField());
        assertEquals("Test", error.getMessage());
    }

    @Test
    void testShouldReturnResponseWithRecipe() {
        PrintRecipeToConsoleRequest request = Mockito.mock(PrintRecipeToConsoleRequest.class);
        Recipe recipe = Mockito.mock(Recipe.class);
        Mockito.when(validator.validate(request.getId())).thenReturn(new ArrayList<>());
        Mockito.when(recipeRepository.getById(request.getId())).thenReturn(Optional.of(recipe));
        PrintRecipeToConsoleResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertNotNull(response.getPrintedRecipe());
        Mockito.verify(recipeRepository).getById(request.getId());
    }

}