package lv.javaguru.java2.cookingApp.core.dto.responses;

import lombok.Getter;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;

import java.util.List;

@Getter
public class AddRecipeResponse extends CoreResponse{

    private Recipe newRecipe;

    public AddRecipeResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddRecipeResponse(Recipe newRecipe) {
        this.newRecipe = newRecipe;
    }
}
