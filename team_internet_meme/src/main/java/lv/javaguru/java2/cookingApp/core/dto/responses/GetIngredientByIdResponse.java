package lv.javaguru.java2.cookingApp.core.dto.responses;

import lombok.Getter;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;

import java.util.List;

@Getter
public class GetIngredientByIdResponse extends CoreResponse{

    private Ingredient ingredient;

    public GetIngredientByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetIngredientByIdResponse(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
