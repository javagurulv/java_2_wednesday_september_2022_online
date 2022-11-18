package lv.javaguru.java2.cookingApp.core.dto.responses;

import lombok.Getter;
import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;

import java.util.List;
import java.util.Optional;

@Getter
public class PrintRecipeToConsoleResponse extends CoreResponse{

    private Optional<Recipe> printedRecipe;
    private List<Ingredient> ingredients;
    private List<CookingStep> cookingSteps;

    public PrintRecipeToConsoleResponse(Optional<Recipe> printedRecipe) {
        this.printedRecipe = printedRecipe;
    }

    public PrintRecipeToConsoleResponse(Optional<Recipe> printedRecipe,
                                        List<Ingredient> ingredients,
                                        List<CookingStep> cookingSteps) {
        this.printedRecipe = printedRecipe;
        this.ingredients = ingredients;
        this.cookingSteps = cookingSteps;
    }

    public PrintRecipeToConsoleResponse(List<CoreError> errors) {
        super(errors);
    }
}
