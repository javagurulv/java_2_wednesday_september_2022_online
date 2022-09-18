package lv.javaguru.java2.cookingApp.requests;

import lombok.Getter;

@Getter
public class PrintRecipeToConsoleRequest {
    private Long id;

    public PrintRecipeToConsoleRequest(Long id) {
        this.id = id;
    }
}
