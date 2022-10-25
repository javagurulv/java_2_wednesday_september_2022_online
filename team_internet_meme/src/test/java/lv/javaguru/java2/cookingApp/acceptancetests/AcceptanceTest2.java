package lv.javaguru.java2.cookingApp.acceptancetests;

import lv.javaguru.java2.cookingApp.DatabaseCleaner;
import lv.javaguru.java2.cookingApp.config.CookingAppConfiguration;
import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.SearchRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.AddRecipeService;
import lv.javaguru.java2.cookingApp.core.services.SearchRecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest2 {

    ApplicationContext appContext;

    @BeforeEach
    void setUp() {
        appContext = new AnnotationConfigApplicationContext(CookingAppConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    void testSearchShouldReturnCorrectListOfRecipes() {
        AddRecipeRequest addRequest1 = new AddRecipeRequest("TestDish2v1",
                List.of(new Ingredient("Test2", "", 1.0)),
                List.of(new CookingStep(1, "Step1")));
        getAddRecipeService().execute(addRequest1);

        AddRecipeRequest request2 = new AddRecipeRequest("TestDish2v2",
                List.of(new Ingredient("Test2", "", 1.0),
                        new Ingredient("Test2v2", "", 1.0)),
                List.of(new CookingStep(1, "Step1")));
        getAddRecipeService().execute(request2);

        AddRecipeRequest request3 = new AddRecipeRequest("TestDish2v3",
                List.of(new Ingredient("Test2v2", "", 1.0),
                        new Ingredient("Test2v3", "", 1.0)),
                List.of(new CookingStep(1, "Step1")));
        getAddRecipeService().execute(request3);

        SearchRecipeRequest searchRecipeRequest = new SearchRecipeRequest(List.of("Test2v2"));

        SearchRecipeResponse response = getSearchRecipeService().execute(searchRecipeRequest);
        assertEquals(2, response.getRecipes().size());
        assertEquals("TestDish2v2", response.getRecipes().get(0).getDishName());
        assertEquals("TestDish2v3", response.getRecipes().get(1).getDishName());
        getDatabaseCleaner().clean();
    }


    private AddRecipeService getAddRecipeService() {
        return appContext.getBean(AddRecipeService.class);
    }

    private SearchRecipeService getSearchRecipeService() {
        return appContext.getBean(SearchRecipeService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
}
