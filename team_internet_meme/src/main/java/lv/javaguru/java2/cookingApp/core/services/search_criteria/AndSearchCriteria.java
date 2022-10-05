package lv.javaguru.java2.cookingApp.core.services.search_criteria;

import lombok.EqualsAndHashCode;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;

@EqualsAndHashCode
public class AndSearchCriteria implements SearchCriteria {

    private SearchCriteria leftCriteria;
    private SearchCriteria rightCriteria;

    public AndSearchCriteria(SearchCriteria leftCriteria, SearchCriteria rightCriteria) {
        this.leftCriteria = leftCriteria;
        this.rightCriteria = rightCriteria;
    }

    @Override
    public boolean test(Recipe recipe) {
        return leftCriteria.test(recipe) && rightCriteria.test(recipe);
    }
}
