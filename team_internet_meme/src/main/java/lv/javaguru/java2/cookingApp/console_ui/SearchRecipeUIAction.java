package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.services.SearchRecipeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchRecipeUIAction implements UIAction {

    private SearchRecipeService searchRecipeService;

    public SearchRecipeUIAction(SearchRecipeService searchRecipeService) {
        this.searchRecipeService = searchRecipeService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        List<String> ingredientNames = new ArrayList<>();
        System.out.println("You can search recipes by their ingredients.");
        boolean addAnotherIngredient = true;
        while (addAnotherIngredient) {
            System.out.println("Enter ingredient name: ");
            String ingredientName = scanner.nextLine();
            ingredientNames.add(ingredientName);
            System.out.println("Add another ingredient to the search?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            Integer userChoice = Integer.parseInt(scanner.nextLine().replaceAll("[^0-9]", ""));
            if (userChoice == 1) {
                continue;
            } else if (userChoice == 2) {
                addAnotherIngredient = false;
            } else {
                System.out.println("You have to choose 1 or 2");
            }
        }
        SearchRecipeRequest request = new SearchRecipeRequest(ingredientNames);
        searchRecipeService.execute(request);
    }
}
