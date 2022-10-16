package lv.javaguru.java2.cookingApp.core.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class DeleteRecipeResponse extends CoreResponse {

    private boolean isRecipeDeleted;

    public DeleteRecipeResponse(List<CoreError> errors) {
        super(errors);
    }
    public DeleteRecipeResponse(boolean isRecipeDeleted) {
        this.isRecipeDeleted = isRecipeDeleted;
    }
}
