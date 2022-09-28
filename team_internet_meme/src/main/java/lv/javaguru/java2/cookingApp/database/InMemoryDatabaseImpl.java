package lv.javaguru.java2.cookingApp.database;

import lv.javaguru.java2.cookingApp.domain.Recipe;
import lv.javaguru.java2.cookingApp.services.search_criteria.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public boolean deleteById(Long id) {
        boolean isRecipeDeleted = false;
        Optional<Recipe> recipeToDeleteOpt = recipes.stream()
                .filter(recipe -> recipe.getId().equals(id))
                .findFirst();
        if (recipeToDeleteOpt.isPresent()) {
            Recipe recipeToDelete = recipeToDeleteOpt.get();
            isRecipeDeleted = recipes.remove(recipeToDelete);
        }
        return isRecipeDeleted;
    }

    @Override
    public Recipe getById(Long id) {
        return recipes.stream().filter(recipe -> recipe.getId().equals(id)).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    @Override
    public List<Recipe> find(SearchCriteria searchCriteria) {
        List<Recipe> recipes = new ArrayList<>();
        return recipes.stream().filter(searchCriteria).collect(Collectors.toList());
    }


}
