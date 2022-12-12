package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.CookingStepRepository;
import lv.javaguru.java2.cookingApp.core.database.IngredientRepository;
import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.dto.requests.PrintRecipeToConsoleRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.dto.responses.PrintRecipeToConsoleResponse;
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
    @Mock private IngredientRepository ingredientRepository;
    @Mock private CookingStepRepository cookingStepRepository;

    @InjectMocks
    private PrintRecipeToConsoleService service;

    @Test
    void testShouldReturnResponseWithErrorWhenRequestIsNotValid() {
        PrintRecipeToConsoleRequest request = new PrintRecipeToConsoleRequest(1L);
        CoreError error = new CoreError("Test", "Test");
        Mockito.when(validator.validate(1L)).thenReturn(List.of(error));
        PrintRecipeToConsoleResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Test", error.getField());
        assertEquals("Test", error.getMessage());
        Mockito.verifyNoInteractions(recipeRepository);
        Mockito.verifyNoInteractions(ingredientRepository);
        Mockito.verifyNoInteractions(cookingStepRepository);
    }

    @Test
    void testShouldReturnResponseWithRecipe() {
        PrintRecipeToConsoleRequest request = new PrintRecipeToConsoleRequest(1L);
        Recipe recipe = new Recipe("test");
        Mockito.when(validator.validate(1L)).thenReturn(new ArrayList<>());
        Mockito.when(recipeRepository.getById(1L)).thenReturn(Optional.of(recipe));
        PrintRecipeToConsoleResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertTrue(response.getPrintedRecipe().isPresent());
        assertEquals(recipe, response.getPrintedRecipe().get());
        Mockito.verify(recipeRepository).getById(1L);
        Mockito.verify(ingredientRepository).getIngredientsByRecipeId(1L);
        Mockito.verify(cookingStepRepository).getCookingStepsByRecipeId(1L);
    }

    @Test
    void testShouldReturnResponseWithNoRecipeIfNoIdInDatabase() {
        PrintRecipeToConsoleRequest request = new PrintRecipeToConsoleRequest(1L);
        Mockito.when(validator.validate(1L)).thenReturn(new ArrayList<>());
        Mockito.when(recipeRepository.getById(request.getId())).thenReturn(Optional.empty());
        PrintRecipeToConsoleResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertTrue(response.getPrintedRecipe().isEmpty());
        Mockito.verify(recipeRepository).getById(1L);
        Mockito.verifyNoInteractions(ingredientRepository);
        Mockito.verifyNoInteractions(cookingStepRepository);
    }

}