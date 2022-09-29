package lv.javaguru.java2.cookingApp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode

public class Recipe {
    private Long id;
    private String dishName;
    private List<Ingredient> ingredients;
    private List<CookingStep> cookingSteps;

    public Recipe(String dishName, List<Ingredient> ingredients, List<CookingStep> cookingSteps) {
        this.dishName = dishName;
        this.ingredients = ingredients;
        this.cookingSteps = cookingSteps;
    }

    @Override
    public String toString() {
        return "ID=" + id + "  " + dishName;
    }

    public void printToConsole() {
        System.out.println("");
        System.out.println(dishName);
        System.out.println("-----------");
        System.out.println("Ingredients");
        System.out.println("-----------");
        ingredients.forEach(System.out::println);
        System.out.println("");
        System.out.println("-----------");
        System.out.println("Cooking steps");
        System.out.println("-----------");
        cookingSteps.forEach(System.out::println);
    }
}
