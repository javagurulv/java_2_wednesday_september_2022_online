package lv.javaguru.java2.cookingApp.core.dto.responses;

import lombok.Getter;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;

import java.util.List;

@Getter
public class SearchRecipeResponse extends CoreResponse {

    private List<Recipe> recipes;

    public SearchRecipeResponse(List<CoreError> errors, List<Recipe> recipes) {
        super(errors);
        this.recipes = recipes;
    }

}
