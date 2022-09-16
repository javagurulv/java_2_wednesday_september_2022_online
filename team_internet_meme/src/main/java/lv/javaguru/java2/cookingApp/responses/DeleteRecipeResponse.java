package lv.javaguru.java2.cookingApp.responses;

import lombok.Getter;
import lv.javaguru.java2.cookingApp.requests.DeleteRecipeRequest;

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
