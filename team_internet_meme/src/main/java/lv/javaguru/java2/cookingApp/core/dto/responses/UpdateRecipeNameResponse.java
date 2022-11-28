package lv.javaguru.java2.cookingApp.core.dto.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class UpdateRecipeNameResponse extends CoreResponse{

    private boolean isUpdated;

    public UpdateRecipeNameResponse(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public UpdateRecipeNameResponse(List<CoreError> errors) {
        super(errors);
    }
}
