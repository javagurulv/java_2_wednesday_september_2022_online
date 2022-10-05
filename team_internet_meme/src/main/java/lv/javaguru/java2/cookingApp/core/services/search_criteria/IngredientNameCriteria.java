package lv.javaguru.java2.cookingApp.core.services.search_criteria;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;

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
