package lv.javaguru.java2.cookingApp.core.dto.requests;

import lombok.Getter;

@Getter
public class DeleteRecipeRequest {
    private Long id;

    public DeleteRecipeRequest(Long id) {
        this.id = id;
    }
}
