package lv.javaguru.java2.cookingApp.consoleui;


import lv.javaguru.java2.cookingApp.core.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.SearchRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.SearchRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class SearchRecipeUIAction implements UIAction {

    @Autowired
    private SearchRecipeService searchRecipeService;

    @Override
    public void execute() {
        SearchRecipeRequest request = getSearchRecipeRequest();
        SearchRecipeResponse response = searchRecipeService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else if (!response.getRecipes().isEmpty()) {
            System.out.println("The list of matching recipes: ");
            response.getRecipes().forEach(System.out::println);
        } else {
            System.out.println("No matching recipes found");
        }
    }

    private SearchRecipeRequest getSearchRecipeRequest() {
        Scanner scanner = new Scanner(System.in);
        List<String> ingredientNames = new ArrayList<>();
        System.out.println("You can search recipes by their ingredients.");
        System.out.println("Enter ingredient name: ");
        String ingredientName = scanner.nextLine();
        ingredientNames.add(ingredientName);

        boolean addAnotherIngredient = true;
        while (addAnotherIngredient) {
            System.out.println("Add another ingredient to the search?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int userChoice = Integer.parseInt(scanner.nextLine().replaceAll("[^0-9]", ""));
            if (userChoice == 1) {
                System.out.println("Enter ingredient name: ");
                String ingredientToAdd = scanner.nextLine();
                ingredientNames.add(ingredientToAdd);;
            } else if (userChoice == 2) {
                addAnotherIngredient = false;
            } else {
                System.out.println("You have to choose 1 or 2");
            }
        }
        return new SearchRecipeRequest(ingredientNames);
    }
}
