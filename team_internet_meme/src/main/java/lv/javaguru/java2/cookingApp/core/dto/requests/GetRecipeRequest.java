package lv.javaguru.java2.cookingApp.core.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRecipeRequest {

    private Long id;

    public GetRecipeRequest() {
    }

    public GetRecipeRequest(Long id) {
        this.id = id;
    }
}
