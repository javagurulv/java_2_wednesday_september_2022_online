package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.IngredientRepository;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.dto.requests.GetIngredientByIdRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.dto.responses.GetIngredientByIdResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class GetIngredientByIdService {

    @Autowired private IngredientRepository ingredientRepository;
    @Autowired private IdValidator validator;

    public GetIngredientByIdResponse execute(GetIngredientByIdRequest request) {
        List<CoreError> errors = new ArrayList<>(validator.validate(request.getId()));
        if (!errors.isEmpty()) {
            return new GetIngredientByIdResponse(errors);
        }
        Optional<Ingredient> ingredient = ingredientRepository.getById(request.getId());
        return ingredientRepository.getById(request.getId())
                .map(GetIngredientByIdResponse::new)
                .orElseGet(()-> {
                    errors.add(new CoreError("Id", "not found"));
                    return new GetIngredientByIdResponse(errors);
                });
    }
}
