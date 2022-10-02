package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.domain.Recipe;
import lv.javaguru.java2.cookingApp.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.services.validators.AddRecipeRequestValidator;
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

    @Mock
    private Database database;

    @Mock
    private AddRecipeRequestValidator validator;

    @InjectMocks
    private AddRecipeService service;

    @Test
    void testShouldReturnResponseWithErrorWhenRequestIsNotValid() {
        AddRecipeRequest request = Mockito.mock(AddRecipeRequest.class);
        AddRecipeResponse response = service.execute(request);
        assertTrue(response.hasErrors());
    }

    @Test
    void testShouldReturnResponseWithAddedRecipe() {
        AddRecipeRequest request = Mockito.mock(AddRecipeRequest.class);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        AddRecipeResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertNotNull(response.getNewRecipe());
        Recipe recipe = response.getNewRecipe();
        Mockito.verify(database).save(recipe);
    }

}