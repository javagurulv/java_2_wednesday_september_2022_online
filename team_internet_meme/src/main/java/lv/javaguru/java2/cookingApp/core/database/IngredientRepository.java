package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    void saveRecipeIngredients(List<Ingredient> ingredients, Long recipeId);
    void save(Ingredient ingredient);
    List<Ingredient> getIngredientsByRecipeId(Long id);
    List<Ingredient> getAllIngredients();
    boolean deleteById(Long id);
    Optional<Ingredient> getById(Long id);
}
