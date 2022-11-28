package lv.javaguru.java2.cookingApp.core.dto.responses;

import lv.javaguru.java2.cookingApp.core.domain.Ingredient;

import java.util.List;

public class GetAllIngredientsResponse extends CoreResponse{

    private List<Ingredient> ingredients;

    public GetAllIngredientsResponse(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
