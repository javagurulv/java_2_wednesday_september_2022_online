package lv.javaguru.java2.cookingApp.requests;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchRecipeRequest {

    private List<String> ingredientName;

    public SearchRecipeRequest(List<String> ingredientName) {
        this.ingredientName = ingredientName;
    }
}
