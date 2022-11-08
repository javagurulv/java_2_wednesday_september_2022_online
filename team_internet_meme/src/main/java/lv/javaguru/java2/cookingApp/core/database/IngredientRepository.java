package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    void saveIngredients(List<Ingredient> ingredients, Long recipeId);
    List<Ingredient> getIngredientsByRecipeId(Long id);
}
