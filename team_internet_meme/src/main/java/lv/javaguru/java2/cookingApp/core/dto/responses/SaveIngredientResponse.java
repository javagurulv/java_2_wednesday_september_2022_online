package lv.javaguru.java2.cookingApp.core.dto.responses;

import lombok.Getter;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;

import java.util.List;

@Getter
public class SaveIngredientResponse extends CoreResponse{
    private Ingredient savedIngredient;

    public SaveIngredientResponse(List<CoreError> errors) {
        super(errors);
    }

    public SaveIngredientResponse(Ingredient savedIngredient) {
        this.savedIngredient = savedIngredient;
    }
}
