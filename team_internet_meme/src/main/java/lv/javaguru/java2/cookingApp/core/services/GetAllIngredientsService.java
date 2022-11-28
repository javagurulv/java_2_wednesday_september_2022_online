package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.IngredientRepository;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.dto.requests.GetAllIngredientsRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.GetAllIngredientsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class GetAllIngredientsService {

    @Autowired private IngredientRepository ingredientRepository;

    public GetAllIngredientsResponse execute (GetAllIngredientsRequest request) {
        List<Ingredient> ingredients = ingredientRepository.getAllIngredients();
        ingredients = ingredients.stream().sorted(Comparator.comparing(Ingredient::getId)).collect(Collectors.toList());
        return new GetAllIngredientsResponse(ingredients);
    }
}
