package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.GetAllRecipesRequest;
import lv.javaguru.java2.cookingApp.core.responses.GetAllRecipesResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetAllRecipesServiceTest {

    @Mock private Database database;

    @InjectMocks
    private GetAllRecipesService service;

    @Test
    void testShouldReturnResponseWhitListOfRecipes() {
        Recipe recipe1 = Mockito.mock(Recipe.class);
        Recipe recipe2 = Mockito.mock(Recipe.class);
        GetAllRecipesRequest request = Mockito.mock(GetAllRecipesRequest.class);

        Mockito.when(database.getAllRecipes()).thenReturn(List.of(recipe1, recipe2));
        GetAllRecipesResponse response = service.execute(request);

        assertEquals(2, response.getRecipes().size());
        assertEquals(recipe1, response.getRecipes().get(0));
        assertEquals(recipe2, response.getRecipes().get(1));
        Mockito.verify(database).getAllRecipes();
    }

}