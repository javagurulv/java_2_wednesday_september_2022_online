package lv.javaguru.java2.cookingApp.core.dto.requests;

import lombok.Getter;

@Getter
public class GetIngredientByIdRequest {

    private Long id;

    public GetIngredientByIdRequest(Long id) {
        this.id = id;
    }
}
