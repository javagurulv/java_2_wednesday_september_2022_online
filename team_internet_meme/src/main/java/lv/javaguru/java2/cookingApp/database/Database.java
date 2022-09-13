package lv.javaguru.java2.cookingApp.database;

import lv.javaguru.java2.cookingApp.Recipe;

import java.util.List;

public interface Database {
    void save(Recipe recipe);
    void deleteById(Long id);
    Recipe getById(Long id);
    List<Recipe> getAllRecipes();
}
