package lv.javaguru.java2.cookingApp.core.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRecipeNameRequest {

    private Long id;
    private String newName;

    public UpdateRecipeNameRequest() {
    }

    public UpdateRecipeNameRequest(Long id, String newName) {
        this.id = id;
        this.newName = newName;
    }
}
