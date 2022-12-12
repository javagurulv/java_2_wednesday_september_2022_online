package lv.javaguru.java2.cookingApp.core.dto.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class DeleteIngredientResponse extends CoreResponse {

    private boolean deleted;

    public DeleteIngredientResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteIngredientResponse(boolean deleted) {
        this.deleted = deleted;
    }
}
