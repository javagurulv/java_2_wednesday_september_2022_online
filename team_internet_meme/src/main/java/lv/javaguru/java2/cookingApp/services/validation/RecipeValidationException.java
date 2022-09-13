package lv.javaguru.java2.cookingApp.services.validation;

public class RecipeValidationException extends RuntimeException {

    public RecipeValidationException(String errorMessage) {
        super(errorMessage);

    }
}
