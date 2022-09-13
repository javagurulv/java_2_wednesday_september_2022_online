package lv.javaguru.java2.cookingApp.services.validation;

import lv.javaguru.java2.cookingApp.Recipe;

public interface RecipeValidationRule {
    void validate(Recipe recipe);

    default void checkNotNull(Recipe recipe){
        if (recipe == null) {
            throw new RecipeValidationException("Recipe cannot be null!");
        }
    }
}
