package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.services.searchcriteria.SearchCriteria;

import java.util.List;

public interface Database {
    Long save(Recipe recipe);
    boolean deleteById(Long id);
    Recipe getById(Long id);
    List<Recipe> getAllRecipes();
    List<Recipe> find(SearchCriteria searchCriteria);
}
