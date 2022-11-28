package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.IngredientRepository;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.dto.requests.SaveIngredientRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.dto.responses.SaveIngredientResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.SaveIngredientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class SaveIngredientService {

    @Autowired private IngredientRepository ingredientRepository;
    @Autowired private SaveIngredientValidator validator;

    public SaveIngredientResponse execute(SaveIngredientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SaveIngredientResponse(errors);
        }
        Ingredient savedIngredient = new Ingredient(request.getIngredientName());
        ingredientRepository.save(savedIngredient);
        return new SaveIngredientResponse(savedIngredient);
    }
}
