package lv.javaguru.java2.cookingApp.database;

import lv.javaguru.java2.cookingApp.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryDatabaseImpl implements Database {
    private Long nextId = 1L;
    private final List<Recipe> recipes = new ArrayList<>();

    @Override
    public void save(Recipe recipe) {
        recipe.setId(nextId);
        nextId++;
        recipes.add(recipe);
    }

    @Override
    public void deleteById(Long id) {
        recipes.removeIf(recipe -> id.equals(recipe.getId()));
    }

    @Override
    public Recipe getById(Long id) {
        return recipes.stream().filter(recipe -> recipe.getId().equals(id)).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipes;
    }
}
