package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.AddRecipeService;
import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;
import lv.javaguru.java2.cookingApp.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@DIComponent
public class AddRecipeUIAction implements UIAction{

    @DIDependency private AddRecipeService addRecipeService;

    @Override
    public void execute() {
        try {
            AddRecipeRequest addRecipeRequest = createRequest();
            AddRecipeResponse response = addRecipeService.execute(addRecipeRequest);
            if (response.hasErrors()) {
                response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " "
                        + coreError.getMessage()));
            } else {
                System.out.println("New recipe id is: " + response.getNewRecipe().getId());
                System.out.println("Recipe was added to database.");
            }
        } catch (NumberFormatException e) {
            System.out.println("You have not entered a number! Please try again.");
        }

    }


    private AddRecipeRequest createRequest() throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the dish");
        String dishName = scanner.nextLine();
        System.out.println("Enter number of ingredients: ");
        String numberStr = scanner.nextLine();

        int numberOfIngredients = Integer.parseInt(numberStr);
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
