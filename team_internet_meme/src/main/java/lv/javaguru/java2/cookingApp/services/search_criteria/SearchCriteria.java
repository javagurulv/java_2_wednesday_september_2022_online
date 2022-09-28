package lv.javaguru.java2.cookingApp.services.search_criteria;

import lv.javaguru.java2.cookingApp.domain.Ingredient;
import lv.javaguru.java2.cookingApp.domain.Recipe;

import java.util.function.Predicate;

public interface SearchCriteria extends Predicate<Recipe> {

    @Override
    boolean test(Recipe recipe);

}
