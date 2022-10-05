package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.responses.SearchRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.search_criteria.AndSearchCriteria;
import lv.javaguru.java2.cookingApp.core.services.search_criteria.IngredientNameCriteria;
import lv.javaguru.java2.cookingApp.core.services.search_criteria.SearchCriteria;
import lv.javaguru.java2.cookingApp.core.services.validators.SearchRecipeRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        Mockito.when(database.find(any())).thenReturn(List.of(recipe1, recipe2));

        SearchRecipeResponse response = service.execute(request);
        assertNotNull(response.getRecipes());

        assertFalse(response.hasErrors());
        assertEquals(recipe1, response.getRecipes().get(0));
        assertEquals(recipe2, response.getRecipes().get(1));

        Mockito.verify(database).find(any());
    }

    @Test
    void testCreateSearchCriteriaWithRequestContainingOneIngredientReturnsExpectedCriteria() {
        SearchRecipeRequest request = new SearchRecipeRequest(List.of("Ingredient1"));
        SearchCriteria expected = new IngredientNameCriteria("Ingredient1");
        SearchCriteria actual = service.createSearchCriteria(request);
        assertEquals(expected, actual);
    }

    @Test
    void testCreateSearchCriteriaWithRequestContainingTwoIngredientsReturnsExpectedCriteria() {
        SearchRecipeRequest request = new SearchRecipeRequest(List.of("Ingredient1", "Ingredient2"));
        SearchCriteria expected = new AndSearchCriteria(new IngredientNameCriteria("Ingredient1"),
                 new IngredientNameCriteria("Ingredient2"));
        SearchCriteria actual = service.createSearchCriteria(request);
        assertEquals(expected, actual);
    }

    @Test
    void testCreateSearchCriteriaWithRequestContainingThreeIngredientsReturnsExpectedCriteria() {
        SearchRecipeRequest request = new SearchRecipeRequest(List.of("Ingredient1", "Ingredient2", "Ingredient3"));
        AndSearchCriteria leftCriteria = new AndSearchCriteria(new IngredientNameCriteria("Ingredient1"),
                 new IngredientNameCriteria("Ingredient2"));
        SearchCriteria expected = new AndSearchCriteria(leftCriteria, new IngredientNameCriteria("Ingredient3"));
        SearchCriteria actual = service.createSearchCriteria(request);
        assertEquals(expected, actual);
    }


}