package lv.javaguru.java2.cookingApp.core.requests;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchRecipeRequest {

    private List<String> ingredientNameList;

    public SearchRecipeRequest(List<String> ingredientName) {
        this.ingredientNameList = ingredientName;
    }
}
