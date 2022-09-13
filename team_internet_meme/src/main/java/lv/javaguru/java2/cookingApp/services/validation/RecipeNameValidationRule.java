package lv.javaguru.java2.cookingApp.services.validation;

import lv.javaguru.java2.cookingApp.Recipe;

public class RecipeNameValidationRule implements RecipeValidationRule{
    @Override
    public void validate(Recipe recipe) {
        checkNotNull(recipe);
        if (recipe.getDishName() == null) {
            throw new RecipeValidationException("Dish name cannot be null.");
        }
    }
}
