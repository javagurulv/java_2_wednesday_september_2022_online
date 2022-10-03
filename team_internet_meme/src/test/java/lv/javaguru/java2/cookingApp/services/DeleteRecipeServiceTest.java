package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.services.validators.DeleteRecipeRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteRecipeServiceTest {

    @Mock private Database database;
    @Mock private DeleteRecipeRequestValidator validator;

    @InjectMocks
    private DeleteRecipeService service;

    @Test
    void testShouldReturnResponseWithErrorWhenRequestIsNotValid() {

    }
}