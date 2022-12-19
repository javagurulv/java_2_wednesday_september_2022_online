package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.IngredientRepository;
import lv.javaguru.java2.cookingApp.core.dto.requests.DeleteIngredientRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.dto.responses.DeleteIngredientResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.DeleteIngredientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class DeleteIngredientService {

    @Autowired private IngredientRepository ingredientRepository;
    @Autowired private DeleteIngredientValidator validator;


    public DeleteIngredientResponse execute(DeleteIngredientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteIngredientResponse(errors);
        }

        boolean deleted = ingredientRepository.deleteById(request.getId());
        return new DeleteIngredientResponse(deleted);
    }
}
