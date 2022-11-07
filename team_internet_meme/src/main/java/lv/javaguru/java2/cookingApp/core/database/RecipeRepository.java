package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository {
    Long save(Recipe recipe);
    boolean deleteById(Long id);
    Optional<Recipe> getById(Long id);
    List<Recipe> getAllRecipes();
    List<Recipe> searchByIngredients(List<String> ingredients);
}
