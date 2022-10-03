package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.domain.Recipe;
import lv.javaguru.java2.cookingApp.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.responses.CoreError;
import lv.javaguru.java2.cookingApp.responses.DeleteRecipeResponse;
import lv.javaguru.java2.cookingApp.services.validators.DeleteRecipeRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteRecipeServiceTest {

    @Mock private Database database;
    @Mock private DeleteRecipeRequestValidator validator;

    @InjectMocks
    private DeleteRecipeService service;

    @Test
    void testShouldReturnResponseWithErrorWhenRequestIsNotValid() {
        DeleteRecipeRequest request = Mockito.mock(DeleteRecipeRequest.class);
        CoreError error = new CoreError("Test", "test");
        Mockito.when(validator.validate(request)).thenReturn(List.of(error));
        DeleteRecipeResponse response = service.execute(request);
        assertTrue(response.hasErrors());
    }

    @Test
    void testShouldReturnResponseTrueWhenRecipeDeleted() {
        DeleteRecipeRequest request = Mockito.mock(DeleteRecipeRequest.class);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteById(request.getId())).thenReturn(true);
        DeleteRecipeResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isRecipeDeleted());
        assertNotNull(request.getId());
        Mockito.verify(database).deleteById(request.getId());
    }
}