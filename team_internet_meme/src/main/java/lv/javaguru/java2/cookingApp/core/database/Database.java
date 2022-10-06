package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.services.search_criteria.SearchCriteria;

import java.util.List;

public interface Database {
    void save(Recipe recipe);
    boolean deleteById(Long id);
    Recipe getById(Long id);
    List<Recipe> getAllRecipes();
    List<Recipe> find(SearchCriteria searchCriteria);
}
