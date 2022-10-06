package lv.javaguru.java2.cookingApp.core.services;

import java.util.ArrayList;
import java.util.List;

import lv.javaguru.java2.cookingApp.core.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.core.services.search_criteria.AndSearchCriteria;
import lv.javaguru.java2.cookingApp.core.services.search_criteria.IngredientNameCriteria;
import lv.javaguru.java2.cookingApp.core.services.search_criteria.SearchCriteria;
import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;

@DIComponent
public class SearchCriteriaBuilder {

	public SearchCriteria build(SearchRecipeRequest request) {
		List<String> ingredients = request.getIngredientNameList();
		int numberOfIngredients = ingredients.size();
		List<SearchCriteria> searchCriteria = new ArrayList<>();
		for (String ingredient : ingredients) {
			searchCriteria.add(new IngredientNameCriteria(ingredient));
		}
		if (numberOfIngredients == 1) {
			return searchCriteria.get(0);
		} else {
			AndSearchCriteria andSearchCriteria = new AndSearchCriteria(searchCriteria.get(0), searchCriteria.get(1));
			int i = 2;
			while (i < numberOfIngredients) {
				andSearchCriteria = new AndSearchCriteria(andSearchCriteria, searchCriteria.get(i));
				i++;
			}
			return andSearchCriteria;
		}
	}

}
