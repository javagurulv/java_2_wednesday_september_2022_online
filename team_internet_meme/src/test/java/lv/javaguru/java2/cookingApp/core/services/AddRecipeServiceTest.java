package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.CookingStepRepository;
import lv.javaguru.java2.cookingApp.core.database.IngredientRepository;
import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.dto.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.services.validators.AddRecipeRequestValidator;
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
class AddRecipeServiceTest {

    @Mock private RecipeRepository recipeRepository;
    @Mock private AddRecipeRequestValidator validator;
    @Mock private IngredientRepository ingredientRepository;
    @Mock private CookingStepRepository cookingStepRepository;

    @InjectMocks
    private AddRecipeService service;

    @Test
    void testShouldReturnResponseWithErrorWhenRequestIsNotValid() {
        AddRecipeRequest request = Mockito.mock(AddRecipeRequest.class);
        CoreError error = new CoreError("Test", "test");
        Mockito.when(validator.validate(request)).thenReturn(List.of(error));
        AddRecipeResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        Mockito.verifyNoInteractions(recipeRepository);
        Mockito.verifyNoInteractions(ingredientRepository);
        Mockito.verifyNoInteractions(cookingStepRepository);
    }

    @Test
    void testShouldReturnResponseWithAddedRecipe() {
        Recipe recipe = new Recipe("test");
        List<Ingredient> ingredients = new ArrayList<>();
        List<CookingStep> cookingSteps = new ArrayList<>();
        AddRecipeRequest request = new AddRecipeRequest("test", ingredients, cookingSteps);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(recipeRepository.save(recipe)).thenReturn(1L);

        AddRecipeResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(recipe, response.getNewRecipe());
        Mockito.verify(recipeRepository).save(recipe);
        Mockito.verify(ingredientRepository).saveIngredients(ingredients, 1L);
        Mockito.verify(cookingStepRepository).saveCookingSteps(cookingSteps, 1L);
    }


}