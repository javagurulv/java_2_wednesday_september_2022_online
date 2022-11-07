package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;

import java.util.Optional;

public interface CookingStepRepository {
    Long save(CookingStep cookingStep);
    boolean deleteByRecipeId(Long recipeId);
    Optional<CookingStep> getById(Long id);
}
