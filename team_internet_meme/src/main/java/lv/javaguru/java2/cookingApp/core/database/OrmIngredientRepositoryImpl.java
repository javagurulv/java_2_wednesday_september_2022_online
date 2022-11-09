package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.Ingredient;

import java.util.List;

public class OrmIngredientRepositoryImpl implements IngredientRepository {
    @Override
    public void saveIngredients(List<Ingredient> ingredients, Long recipeId) {

    }

    @Override
    public List<Ingredient> getIngredientsByRecipeId(Long id) {
        return null;
    }
}
