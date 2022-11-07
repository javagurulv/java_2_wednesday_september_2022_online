package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    Long save(Ingredient ingredient);
    boolean deleteByRecipeId(Long recipeId);
    Optional<Ingredient> getById(Long id);
}
