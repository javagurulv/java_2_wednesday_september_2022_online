package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;

import java.util.List;
import java.util.Optional;

public interface CookingStepRepository {
    void saveCookingSteps(List<CookingStep> cookingSteps, Long recipeId);
    List<CookingStep> getCookingStepsByRecipeId(Long id);
}
