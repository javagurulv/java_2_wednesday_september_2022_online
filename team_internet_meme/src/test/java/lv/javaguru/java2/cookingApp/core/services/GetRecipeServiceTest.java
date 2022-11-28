package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.dto.requests.GetRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.dto.responses.GetRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.IdValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetRecipeServiceTest {

    @Mock private RecipeRepository recipeRepository;
    @Mock private IdValidator validator;

    @InjectMocks
    private GetRecipeService service;

    @Test
    void testResponseHasErrorsWhenRequestNotValid() {
        Recipe recipe = new Recipe("test");
        GetRecipeRequest request = new GetRecipeRequest(1L);
        Mockito.when(validator.validate(request.getId()))
                .thenReturn(List.of(new CoreError("test", "test")));
        GetRecipeResponse response = service.execute(request);
        assertTrue(response.hasErrors());

        Mockito.verifyNoInteractions(recipeRepository);
    }

    @Test
    void testReturnsRecipeWhenRequestIsValidAndIdIsExistsInDatabase() {
        Recipe recipe = new Recipe("test");
        GetRecipeRequest request = new GetRecipeRequest(1L);
        Mockito.when(validator.validate(request.getId())).thenReturn(List.of());
        Mockito.when(recipeRepository.getById(request.getId())).thenReturn(Optional.of(recipe));
        GetRecipeResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(recipe,response.getRecipe());
    }

    @Test
    void testReturnsErrorWhenIdNotFoundInDatabase() {
        Recipe recipe = new Recipe("test");
        GetRecipeRequest request = new GetRecipeRequest(1L);
        Mockito.when(validator.validate(request.getId())).thenReturn(List.of());
        Mockito.when(recipeRepository.getById(request.getId())).thenReturn(Optional.empty());
        GetRecipeResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1,response.getErrors().size());
        assertEquals("Id", response.getErrors().get(0).getField());
        assertEquals("Not found", response.getErrors().get(0).getMessage());
    }
}
