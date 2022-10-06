package lv.javaguru.java2.cookingApp.core.services.searchcriteria;

import lombok.EqualsAndHashCode;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;

@EqualsAndHashCode
public class IngredientNameCriteria implements SearchCriteria {

    private String ingredientName;

    public IngredientNameCriteria(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public boolean test(Recipe recipe) {

        return recipe.getIngredients().stream().anyMatch(ingredient -> ingredient.getName().equals(ingredientName));

    }
}
