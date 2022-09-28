package lv.javaguru.java2.cookingApp.services.search_criteria;

import lv.javaguru.java2.cookingApp.domain.Ingredient;
import lv.javaguru.java2.cookingApp.domain.Recipe;

public class AndCriteria implements SearchCriteria {

    private SearchCriteria leftCriteria;
    private SearchCriteria rightCriteria;

    public AndCriteria(SearchCriteria leftCriteria, SearchCriteria rightCriteria) {
        this.leftCriteria = leftCriteria;
        this.rightCriteria = rightCriteria;
    }

    @Override
    public boolean test(Recipe recipe) {
        return leftCriteria.test(recipe) && rightCriteria.test(recipe);
    }
}
