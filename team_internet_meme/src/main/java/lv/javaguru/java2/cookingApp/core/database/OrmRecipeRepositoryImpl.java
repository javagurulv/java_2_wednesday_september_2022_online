package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;

import java.util.List;
import java.util.Optional;

public class OrmRecipeRepositoryImpl implements RecipeRepository {
    @Override
    public Long save(Recipe recipe) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return null;
    }

    @Override
    public List<Recipe> searchByIngredients(List<String> ingredients) {
        return null;
    }
}
