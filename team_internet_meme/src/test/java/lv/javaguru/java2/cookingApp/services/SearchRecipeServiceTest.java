package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.CoreError;
import lv.javaguru.java2.cookingApp.responses.SearchRecipeResponse;
import lv.javaguru.java2.cookingApp.services.validators.SearchRecipeRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

//    @Test
//    void testShouldReturnResponseWithListOfRecipes() {
//        SearchRecipeRequest request = Mockito.mock(SearchRecipeRequest.class);
//        Recipe recipe1 = Mockito.mock(Recipe.class);
//        Recipe recipe2 = Mockito.mock(Recipe.class);
//        SearchCriteria searchCriteria = new AndSearchCriteria(null, null);
//        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
//        Mockito.when(database.find(searchCriteria)).thenReturn(List.of(recipe1, recipe2));
//
//        SearchRecipeResponse response = service.execute(request);
//        assertNotNull(response.getRecipes());
//
//        assertFalse(response.hasErrors());
//        assertEquals(recipe1, response.getRecipes().get(0));
//        assertEquals(recipe2, response.getRecipes().get(1));
//
//        Mockito.verify(database).find(searchCriteria);
//    }
//

}