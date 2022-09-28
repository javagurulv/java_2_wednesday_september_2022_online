package lv.javaguru.java2.cookingApp.services.search_criteria;

import lv.javaguru.java2.cookingApp.domain.Ingredient;
import lv.javaguru.java2.cookingApp.domain.Recipe;

public class IngredientNameCriteria implements SearchCriteria {

    private String ingredientName;

    public IngredientNameCriteria(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public boolean test(Recipe recipe) {

        return recipe.getIngredients().stream().map(Ingredient::getName)
                .anyMatch(s -> s.equalsIgnoreCase(ingredientName));
    }
}
