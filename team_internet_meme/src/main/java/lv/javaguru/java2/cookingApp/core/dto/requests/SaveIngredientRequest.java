package lv.javaguru.java2.cookingApp.core.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveIngredientRequest {

    private String ingredientName;

    public SaveIngredientRequest() {
    }

    public SaveIngredientRequest(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
