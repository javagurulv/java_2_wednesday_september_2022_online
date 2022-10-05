package lv.javaguru.java2.cookingApp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;

import java.util.List;

@Getter
public class PrintRecipeToConsoleResponse extends CoreResponse{

    private Recipe printedRecipe;

    public PrintRecipeToConsoleResponse(Recipe printedRecipe) {
        this.printedRecipe = printedRecipe;
    }

    public PrintRecipeToConsoleResponse(List<CoreError> errors) {
        super(errors);
    }
}