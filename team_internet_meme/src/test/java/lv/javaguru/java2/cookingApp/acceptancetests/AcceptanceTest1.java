package lv.javaguru.java2.cookingApp.acceptancetests;

import lv.javaguru.java2.cookingApp.DatabaseCleaner;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
public class AcceptanceTest1 {

    private ApplicationContext appContext;

    @BeforeEach
    public void setUp() {
        appContext = new AnnotationConfigApplicationContext(CookingAppConfiguration.class);
        getDatabaseCleaner().clean();
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
        getDatabaseCleaner().clean();
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
        getDatabaseCleaner().clean();
    }

    private AddRecipeService getAddRecipeService() {
        return appContext.getBean(AddRecipeService.class);
    }

    private GetAllRecipesService getAllRecipesService() {
        return appContext.getBean(GetAllRecipesService.class);
    }

    private DeleteRecipeService getDeleteRecipeService() {
        return appContext.getBean(DeleteRecipeService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
}
