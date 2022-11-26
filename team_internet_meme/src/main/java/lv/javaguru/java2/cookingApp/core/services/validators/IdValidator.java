package lv.javaguru.java2.cookingApp.core.services.validators;


import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IdValidator {

    public List<CoreError> validate(Long id) {
        List<CoreError> errors = new ArrayList<>();
        if (id == null) {
            errors.add(new CoreError("Recipe ID", "Cannot be null"));
        }
        return errors;
    }
}
