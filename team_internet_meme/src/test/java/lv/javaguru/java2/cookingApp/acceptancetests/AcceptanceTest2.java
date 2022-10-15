//package lv.javaguru.java2.cookingApp.acceptancetests;
//
//import lv.javaguru.java2.cookingApp.config.CookingAppConfiguration;
//import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
//import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
//import lv.javaguru.java2.cookingApp.core.requests.AddRecipeRequest;
//import lv.javaguru.java2.cookingApp.core.requests.GetAllRecipesRequest;
//import lv.javaguru.java2.cookingApp.core.requests.SearchRecipeRequest;
//import lv.javaguru.java2.cookingApp.core.responses.GetAllRecipesResponse;
//import lv.javaguru.java2.cookingApp.core.responses.SearchRecipeResponse;
//import lv.javaguru.java2.cookingApp.core.services.AddRecipeService;
//import lv.javaguru.java2.cookingApp.core.services.GetAllRecipesService;
//import lv.javaguru.java2.cookingApp.core.services.SearchRecipeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class AcceptanceTest2 {
//
//    ApplicationContext applicationContext;
//
//    @BeforeEach
//    void setUp() {
//        applicationContext = new AnnotationConfigApplicationContext(CookingAppConfiguration.class);
//    }
//
//    @Test
//    void testSearchShouldReturnCorrectListOfRecipes() {
//        AddRecipeRequest addRequest1 = new AddRecipeRequest("TestDish2v1",
//                List.of(new Ingredient("Test2", "", 1.0)),
//                List.of(new CookingStep(1, "Step1")));
//        getAddRecipeService().execute(addRequest1);
//
//        AddRecipeRequest request2 = new AddRecipeRequest("TestDish2v2",
//                List.of(new Ingredient("Test2", "", 1.0),
//                        new Ingredient("Test2v2", "", 1.0)),
//                List.of(new CookingStep(1, "Step1")));
//        getAddRecipeService().execute(request2);
//
//        AddRecipeRequest request3 = new AddRecipeRequest("TestDish2v3",
//                List.of(new Ingredient("Test2v2", "", 1.0),
//                        new Ingredient("Test2v3", "", 1.0)),
//                List.of(new CookingStep(1, "Step1")));
//        getAddRecipeService().execute(request3);
//
//        SearchRecipeRequest searchRecipeRequest = new SearchRecipeRequest(List.of("Test2v2"));
//
//        SearchRecipeResponse response = getSearchRecipeService().execute(searchRecipeRequest);
//        assertEquals(2, response.getRecipes().size());
//        assertEquals("TestDish2v2", response.getRecipes().get(0).getDishName());
//        assertEquals("TestDish2v3", response.getRecipes().get(1).getDishName());
//    }
//
//
//    private AddRecipeService getAddRecipeService() {
//        return applicationContext.getBean(AddRecipeService.class);
//    }
//
//    private SearchRecipeService getSearchRecipeService() {
//        return applicationContext.getBean(SearchRecipeService.class);
//    }
//}
