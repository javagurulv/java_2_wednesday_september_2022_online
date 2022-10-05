package lv.javaguru.java2.cookingApp.core.services.search_criteria;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;

import java.util.function.Predicate;

public interface SearchCriteria extends Predicate<Recipe> {

    @Override
    boolean test(Recipe recipe);

}
