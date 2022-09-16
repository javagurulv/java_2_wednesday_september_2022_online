package lv.javaguru.java2.cookingApp.requests;

import lombok.Getter;
import lv.javaguru.java2.cookingApp.CookingStep;
import lv.javaguru.java2.cookingApp.Ingredient;

import java.util.List;

@Getter
public class AddRecipeRequest {
    private String dishName;
    private List<Ingredient> ingredients;
    private List<CookingStep> cookingSteps;

    public AddRecipeRequest(String dishName, List<Ingredient> ingredients, List<CookingStep> cookingSteps) {
        this.dishName = dishName;
        this.ingredients = ingredients;
        this.cookingSteps = cookingSteps;
    }
}
