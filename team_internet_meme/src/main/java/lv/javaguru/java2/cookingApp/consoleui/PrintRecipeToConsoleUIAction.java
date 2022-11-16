package lv.javaguru.java2.cookingApp.consoleui;


import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.PrintRecipeToConsoleRequest;
import lv.javaguru.java2.cookingApp.core.responses.PrintRecipeToConsoleResponse;
import lv.javaguru.java2.cookingApp.core.services.PrintRecipeToConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class PrintRecipeToConsoleUIAction implements UIAction{

    @Autowired
    private PrintRecipeToConsoleService printRecipeToConsoleService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID of a recipe to print: ");
        Long id = Long.parseLong(scanner.nextLine());
        PrintRecipeToConsoleRequest request = new PrintRecipeToConsoleRequest(id);
        PrintRecipeToConsoleResponse response = printRecipeToConsoleService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " "
                    + coreError.getMessage()));
        } else if (response.getPrintedRecipe().isPresent()) {
            Recipe recipe = response.getPrintedRecipe().get();
            List<Ingredient> ingredients = response.getIngredients();
            List<CookingStep> cookingSteps = response.getCookingSteps();
            printRecipe(recipe, ingredients, cookingSteps);
        } else if (response.getPrintedRecipe().isEmpty()) {
            System.out.println("No recipe with id " + id + " found in the database");
        }
    }

    private void printRecipe(Recipe recipe, List<Ingredient> ingredients, List<CookingStep> cookingSteps) {
        System.out.println();
        System.out.println(recipe.getDishName());
        System.out.println();
        System.out.println("Ingredients:");
        ingredients.forEach(System.out::println);
        System.out.println();
        System.out.println("Cooking steps:");
        cookingSteps.forEach(System.out::println);
        System.out.println();
    }
}
