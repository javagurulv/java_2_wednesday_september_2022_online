package lv.javaguru.java2.cookingApp.core.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteRecipeRequest {
    private Long id;


    public DeleteRecipeRequest() {
    }

    public DeleteRecipeRequest(Long id) {
        this.id = id;
    }
}
