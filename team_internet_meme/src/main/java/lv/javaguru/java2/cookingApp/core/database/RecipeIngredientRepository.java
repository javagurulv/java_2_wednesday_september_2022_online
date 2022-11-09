package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.RecipeIngredient;

import java.util.Optional;

public interface RecipeIngredientRepository {

    Long save(RecipeIngredient recipeIngredient);

    Optional<RecipeIngredient> getById(Long Id);
}
