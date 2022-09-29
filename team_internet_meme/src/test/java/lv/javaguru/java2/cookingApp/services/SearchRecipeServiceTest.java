package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.cookingApp.domain.CookingStep;
import lv.javaguru.java2.cookingApp.domain.Ingredient;
import lv.javaguru.java2.cookingApp.domain.Recipe;
import lv.javaguru.java2.cookingApp.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.SearchRecipeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchRecipeServiceTest {

    Database database;
    SearchRecipeService searchRecipeService;

    @BeforeEach
    void setUp() {
        database = new InMemoryDatabaseImpl();
        Ingredient eggs = new Ingredient("eggs", "", 2.0);
        Ingredient bacon = new Ingredient("bacon", "", 1.0);
        Ingredient sausage = new Ingredient("sausage", "", 1.0);
        Ingredient beans = new Ingredient("beans","", 1.0);
        List<Ingredient> ingredientList1 = new ArrayList<>();
        ingredientList1.add(eggs);
        ingredientList1.add(bacon);
        List<Ingredient> ingredientList2 = new ArrayList<>();
        ingredientList2.add(eggs);
        ingredientList2.add(bacon);
        ingredientList2.add(sausage);
        List<Ingredient> ingredientList3 = new ArrayList<>();
        ingredientList3.add(eggs);
        ingredientList3.add(bacon);
        ingredientList3.add(beans);
        List<Ingredient> ingredientList4 = new ArrayList<>();
        ingredientList4.add(eggs);
        ingredientList4.add(bacon);
        ingredientList4.add(beans);
        ingredientList4.add(sausage);
        CookingStep cookingStep = new CookingStep(1, "cook");
        List<CookingStep> cookingSteps = new ArrayList<>();
        cookingSteps.add(cookingStep);

        Recipe eggsAndBacon = new Recipe("Eggs and bacon", ingredientList1, cookingSteps);
        Recipe eggsBaconAndSausage = new Recipe("Eggs, bacon and sausage", ingredientList2, cookingSteps);
        Recipe eggsBaconAndBeans = new Recipe("Eggs, bacon and beans", ingredientList3, cookingSteps);
        Recipe eggsBaconBeansAndSausage = new Recipe("Eggs, bacon, beans and sausage", ingredientList4, cookingSteps);
        database.save(eggsAndBacon);
        database.save(eggsBaconAndSausage);
        database.save(eggsBaconAndBeans);
        database.save(eggsBaconBeansAndSausage);
        searchRecipeService = new SearchRecipeService(database);
    }

    @Test
    void testSearchBy2IngredientsShouldReturn4Recipes() {
        List<String> ingredientList1 = new ArrayList<>();
        ingredientList1.add("eggs");
        ingredientList1.add("bacon");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredientList1);
        SearchRecipeResponse response = searchRecipeService.execute(request);
        List<Recipe> recipes = response.getRecipes();
        assertEquals(4, recipes.size());
        assertEquals("Eggs and bacon", recipes.get(0).getDishName());
        assertEquals("Eggs, bacon and sausage", recipes.get(1).getDishName());
        assertEquals("Eggs, bacon and beans", recipes.get(2).getDishName());
        assertEquals("Eggs, bacon, beans and sausage", recipes.get(3).getDishName());
    }

    @Test
    void testSearchBy2IngredientsShouldReturn1Recipe() {
        List<String> ingredientList1 = new ArrayList<>();
        ingredientList1.add("sausage");
        ingredientList1.add("beans");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredientList1);
        SearchRecipeResponse response = searchRecipeService.execute(request);
        List<Recipe> recipes = response.getRecipes();
        assertEquals(1, recipes.size());
        assertEquals("Eggs, bacon, beans and sausage", recipes.get(0).getDishName());
    }

    @Test
    void testSearchBy4IngredientsShouldReturn1Recipe() {
        List<String> ingredientList1 = new ArrayList<>();
        ingredientList1.add("sausage");
        ingredientList1.add("beans");
        ingredientList1.add("eggs");
        ingredientList1.add("bacon");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredientList1);
        SearchRecipeResponse response = searchRecipeService.execute(request);
        List<Recipe> recipes = response.getRecipes();
        assertEquals(1, recipes.size());
        assertEquals("Eggs, bacon, beans and sausage", recipes.get(0).getDishName());
    }

    @Test
    void testSearchBy1IngredientShouldReturnEmptyListAndNoErrors() {
        List<String> ingredientList1 = new ArrayList<>();
        ingredientList1.add("not existing ingredient");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredientList1);
        SearchRecipeResponse response = searchRecipeService.execute(request);
        List<Recipe> recipes = response.getRecipes();
        assertFalse(response.hasErrors());
        assertTrue(recipes.isEmpty());
    }
}