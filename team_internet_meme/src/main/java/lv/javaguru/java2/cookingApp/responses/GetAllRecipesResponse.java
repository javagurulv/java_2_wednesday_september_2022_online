package lv.javaguru.java2.cookingApp.responses;

import lombok.Getter;
import lv.javaguru.java2.cookingApp.Recipe;

import java.util.List;

@Getter
public class GetAllRecipesResponse {
    public List<Recipe> recipes;

    public GetAllRecipesResponse(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
