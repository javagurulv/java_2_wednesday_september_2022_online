package lv.javaguru.java2.cookingApp.core.dto.responses;

import lombok.Getter;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;

import java.util.List;

@Getter
public class GetRecipeResponse extends CoreResponse {

    private Recipe recipe;

    public GetRecipeResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetRecipeResponse(Recipe recipe) {
        this.recipe = recipe;
    }
}
