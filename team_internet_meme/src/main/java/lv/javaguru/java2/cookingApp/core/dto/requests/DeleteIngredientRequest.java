package lv.javaguru.java2.cookingApp.core.dto.requests;

import lombok.Getter;

@Getter
public class DeleteIngredientRequest {

    private Long id;

    public DeleteIngredientRequest() {
    }

    public DeleteIngredientRequest(Long id) {
        this.id = id;
    }
}
