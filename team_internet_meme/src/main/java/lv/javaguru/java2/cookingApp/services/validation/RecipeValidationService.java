package lv.javaguru.java2.cookingApp.services.validation;

import lv.javaguru.java2.cookingApp.Recipe;

import java.util.HashSet;
import java.util.Set;

public class RecipeValidationService {

    private Set<RecipeValidationRule> validationRules = new HashSet<>();

    public RecipeValidationService() {
        validationRules.add(new RecipeNameValidationRule());
    }

    public void validate(Recipe recipe) {
        validationRules.forEach(recipeValidationRule -> recipeValidationRule.validate(recipe));
    }
}
