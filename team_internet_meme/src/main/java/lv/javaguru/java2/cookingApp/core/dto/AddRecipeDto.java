package lv.javaguru.java2.cookingApp.core.dto;

import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;

import java.util.List;

@Getter
@Setter
public class AddRecipeDto {

    private String dishName;
    private List<Ingredient> ingredients;
    private List<CookingStep> cookingSteps;

    public AddRecipeDto() {
    }

    public AddRecipeDto(String dishName, List<Ingredient> ingredients, List<CookingStep> cookingSteps) {
        this.dishName = dishName;
        this.ingredients = ingredients;
        this.cookingSteps = cookingSteps;
    }
}
