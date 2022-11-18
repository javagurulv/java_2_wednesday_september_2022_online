package lv.javaguru.java2.cookingApp.core.dto;

import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class IngredientsListDto {
    private List<Ingredient> ingredients;

    public IngredientsListDto() {
        ingredients = new ArrayList<>();
    }

    public IngredientsListDto(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

}
