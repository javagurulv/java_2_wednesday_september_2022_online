package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.CookingStep;
import lv.javaguru.java2.cookingApp.Ingredient;
import lv.javaguru.java2.cookingApp.Recipe;
import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.services.validation.RecipeValidationService;

import java.util.List;

public class AddRecipeService {
    private Database database;
    private RecipeValidationService recipeValidationService;

    public AddRecipeService(Database database) {
        this.database = database;
    }

    public void execute(String dishName, List<Ingredient> ingredients, List<CookingStep> cookingSteps) {
        Recipe recipe = new Recipe(dishName, ingredients, cookingSteps);
       // recipeValidationService.validate(recipe);
        database.save(recipe);
    }
}
