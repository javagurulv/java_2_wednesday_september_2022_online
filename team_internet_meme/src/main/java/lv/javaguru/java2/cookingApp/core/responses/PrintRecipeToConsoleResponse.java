package lv.javaguru.java2.cookingApp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;

import java.util.List;
import java.util.Optional;

@Getter
public class PrintRecipeToConsoleResponse extends CoreResponse{

    private Optional<Recipe> printedRecipe;

    public PrintRecipeToConsoleResponse(Optional<Recipe> printedRecipe) {
        this.printedRecipe = printedRecipe;
    }

    public PrintRecipeToConsoleResponse(List<CoreError> errors) {
        super(errors);
    }
}
