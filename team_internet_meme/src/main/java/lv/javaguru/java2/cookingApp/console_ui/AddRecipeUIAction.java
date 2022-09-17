package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.domain.CookingStep;
import lv.javaguru.java2.cookingApp.domain.Ingredient;
import lv.javaguru.java2.cookingApp.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.services.AddRecipeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddRecipeUIAction implements UIAction{
    private AddRecipeService addRecipeService;

    public AddRecipeUIAction(AddRecipeService addRecipeService) {
        this.addRecipeService = addRecipeService;
    }

    @Override
    public void execute() {
        AddRecipeRequest addRecipeRequest = createRequest();
        AddRecipeResponse response = addRecipeService.execute(addRecipeRequest);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " "
                    + coreError.getMessage()));
        } else {
            System.out.println("New recipe id is: " + response.getNewRecipe().getId());
            System.out.println("Recipe was added to database.");
        }
    }


    private AddRecipeRequest createRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the dish");
        String dishName = scanner.nextLine();
        System.out.println("Enter number of ingredients: ");
        int numberOfIngredients = Integer.parseInt(scanner.nextLine());
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 1; i <= numberOfIngredients ; i++) {
            System.out.println("Ingredient " + i);
            System.out.println("Enter ingredient: ");
            String ingredientName = scanner.nextLine();
            System.out.println("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter measurement: ");
            String measurement = scanner.nextLine();
            Ingredient ingredient = new Ingredient(ingredientName, measurement, amount);
            ingredients.add(ingredient);
        }
        System.out.println("Enter number of steps");
        int numberOfSteps = Integer.parseInt(scanner.nextLine());
        List<CookingStep> cookingSteps = new ArrayList<>();
        for (int i = 1; i <= numberOfSteps; i++) {
            System.out.println("Enter cooking step " + i + ": ");
            String stepInstructions = scanner.nextLine();
            CookingStep cookingStep = new CookingStep(i, stepInstructions);
            cookingSteps.add(cookingStep);
        }

        return new AddRecipeRequest(dishName, ingredients, cookingSteps);
    }
}
