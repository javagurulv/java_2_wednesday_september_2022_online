package lv.javaguru.java2.cookingApp.core.database;


import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.services.searchcriteria.SearchCriteria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class InMemoryDatabaseImpl implements Database {
    private Long nextId = 1L;
    private final List<Recipe> recipesDB = new ArrayList<>();

    @Override
    public Long save(Recipe recipe) {
        recipe.setId(nextId);
        nextId++;
        recipesDB.add(recipe);
        return nextId;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isRecipeDeleted = false;
        Optional<Recipe> recipeToDeleteOpt = recipesDB.stream()
                .filter(recipe -> recipe.getId().equals(id))
                .findFirst();
        if (recipeToDeleteOpt.isPresent()) {
            Recipe recipeToDelete = recipeToDeleteOpt.get();
            isRecipeDeleted = recipesDB.remove(recipeToDelete);
        }
        return isRecipeDeleted;
    }

    @Override
    public Recipe getById(Long id) {
        return recipesDB.stream().filter(recipe -> recipe.getId().equals(id)).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipesDB);
    }

    @Override
    public List<Recipe> find(SearchCriteria searchCriteria) {
        return recipesDB.stream().filter(searchCriteria).collect(Collectors.toList());
    }


}
