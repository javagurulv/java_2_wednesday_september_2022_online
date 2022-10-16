package lv.javaguru.java2.cookingApp.acceptancetests;

import lv.javaguru.java2.cookingApp.config.CookingAppConfiguration;
import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.requests.GetAllRecipesRequest;
import lv.javaguru.java2.cookingApp.core.responses.GetAllRecipesResponse;
import lv.javaguru.java2.cookingApp.core.services.AddRecipeService;
import lv.javaguru.java2.cookingApp.core.services.DeleteRecipeService;
import lv.javaguru.java2.cookingApp.core.services.GetAllRecipesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptanceTest1 {

    ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(CookingAppConfiguration.class);
    }

    @Test
    void testShouldReturnListOfAllRecipes() {
        AddRecipeRequest request1 = new AddRecipeRequest("TestDish1", List.of(new Ingredient("Test1", "", 1.0)),
                List.of(new CookingStep(1, "Step1")));
        getAddRecipeService().execute(request1);

        AddRecipeRequest request2 = new AddRecipeRequest("TestDish1v2", List.of(new Ingredient("Test1", "", 1.0)),
                List.of(new CookingStep(1, "Step1")));
        getAddRecipeService().execute(request2);

        GetAllRecipesResponse response = getAllRecipesService().execute(new GetAllRecipesRequest());
        assertEquals(2, response.getRecipes().size());
    }

    @Test
    void testShouldReturnEmptyListOfRecipes() {
        AddRecipeRequest request1 = new AddRecipeRequest("TestDish1", List.of(new Ingredient("Test1", "", 1.0)),
                List.of(new CookingStep(1, "Step1")));
        getAddRecipeService().execute(request1);

        AddRecipeRequest request2 = new AddRecipeRequest("TestDish1v2", List.of(new Ingredient("Test1", "", 1.0)),
                List.of(new CookingStep(1, "Step1")));
        getAddRecipeService().execute(request2);

        GetAllRecipesResponse response = getAllRecipesService().execute(new GetAllRecipesRequest());

        DeleteRecipeRequest deleteRecipeRequest1 = new DeleteRecipeRequest(response.getRecipes().get(0).getId());
        DeleteRecipeRequest deleteRecipeRequest2 = new DeleteRecipeRequest(response.getRecipes().get(1).getId());
        getDeleteRecipeService().execute(deleteRecipeRequest1);
        getDeleteRecipeService().execute(deleteRecipeRequest2);

        GetAllRecipesResponse response1 = getAllRecipesService().execute(new GetAllRecipesRequest());
        assertTrue(response1.getRecipes().isEmpty());
    }

    private AddRecipeService getAddRecipeService() {
        return applicationContext.getBean(AddRecipeService.class);
    }

    private GetAllRecipesService getAllRecipesService() {
        return applicationContext.getBean(GetAllRecipesService.class);
    }

    private DeleteRecipeService getDeleteRecipeService() {
        return applicationContext.getBean(DeleteRecipeService.class);
    }
}
