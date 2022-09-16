package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.CookingStep;
import lv.javaguru.java2.cookingApp.Ingredient;
import lv.javaguru.java2.cookingApp.requests.AddRecipeRequest;
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
        addRecipeService.execute(addRecipeRequest);
    }

    private List<Ingredient> createIngredientsList() {
        Scanner scanner = new Scanner(System.in);
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
        return ingredients;
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
