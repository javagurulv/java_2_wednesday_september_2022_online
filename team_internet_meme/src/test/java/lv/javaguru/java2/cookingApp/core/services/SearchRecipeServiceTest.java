package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.responses.SearchRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.SearchRecipeRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class SearchRecipeServiceTest {

    @Mock private Database database;
    @Mock private SearchRecipeRequestValidator validator;

    @InjectMocks
    private SearchRecipeService service;

	@Captor
	ArgumentCaptor<List<String>> ingredientListCaptor;

    @Test
    void testShouldReturnResponseWithErrorWhenRequestIsNotValid() {
        SearchRecipeRequest request = Mockito.mock(SearchRecipeRequest.class);
        CoreError error = new CoreError("Test", "Test");
        Mockito.when(validator.validate(request)).thenReturn(List.of(error));
        SearchRecipeResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Test", error.getField());
        assertEquals("Test", error.getMessage());
    }


    @Test
    void testShouldReturnResponseWithListOfRecipes() {
        SearchRecipeRequest request = new SearchRecipeRequest(List.of("Ingredient1","Ingredient2"));
        Recipe recipe1 = Mockito.mock(Recipe.class);
        Recipe recipe2 = Mockito.mock(Recipe.class);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.searchByIngredients(any())).thenReturn(List.of(recipe1, recipe2));
        SearchRecipeResponse response = service.execute(request);
        assertNotNull(response.getRecipes());
        assertFalse(response.hasErrors());
        assertEquals(recipe1, response.getRecipes().get(0));
        assertEquals(recipe2, response.getRecipes().get(1));

		Mockito.verify(database).searchByIngredients(ingredientListCaptor.capture());
		List<String> captorValue = ingredientListCaptor.getValue();
        assertEquals(List.of("Ingredient1","Ingredient2"), captorValue);
    }

}