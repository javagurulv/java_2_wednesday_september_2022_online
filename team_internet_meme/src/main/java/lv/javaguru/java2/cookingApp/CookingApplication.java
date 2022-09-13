package lv.javaguru.java2.cookingApp;

import lv.javaguru.java2.cookingApp.console_ui.*;
import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.cookingApp.services.AddRecipeService;
import lv.javaguru.java2.cookingApp.services.DeleteRecipeService;
import lv.javaguru.java2.cookingApp.services.GetAllRecipesService;
import lv.javaguru.java2.cookingApp.services.PrintRecipeToConsoleService;
import lv.javaguru.java2.cookingApp.services.validation.RecipeValidationService;

import java.util.Scanner;

public class CookingApplication {
    private static Database inMemoryDatabase = new InMemoryDatabaseImpl();
    private static AddRecipeService addRecipeService = new AddRecipeService(inMemoryDatabase);
    private static DeleteRecipeService deleteRecipeService = new DeleteRecipeService(inMemoryDatabase);
    private static GetAllRecipesService getAllRecipesService = new GetAllRecipesService(inMemoryDatabase);
    private static PrintRecipeToConsoleService printRecipeToConsoleService = new PrintRecipeToConsoleService(inMemoryDatabase);
    private static UIAction addRecipeUIAction = new AddRecipeUIAction(addRecipeService);
    private static UIAction deleteRecipeUIAction = new DeleteRecipeUIAction(deleteRecipeService);
    private static UIAction getAllRecipesUIAction = new GetAllRecipesUIAction(getAllRecipesService);
    private static UIAction printRecipeUIAction = new PrintRecipeToConsoleUIAction(printRecipeToConsoleService);
    private static UIAction exitUIAction = new ExitUIAction();

    public static void main(String[] args) {
        while (true) {
            printProgramMenu();
            int userChoice = getMenuNumberFromUser();
            executeSelectedMenuItem(userChoice);
        }
    }

    private static void printProgramMenu() {
        System.out.println();
        System.out.println("Program menu:");
        System.out.println("1. Add recipe to list");
        System.out.println("2. Delete recipe from list");
        System.out.println("3. Show all recipes in the list");
        System.out.println("4. Print recipe to console");
        System.out.println("5. Exit");
        System.out.println();
    }

    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void executeSelectedMenuItem(int selectedMenu) {
        switch (selectedMenu) {
            case 1 -> addRecipeUIAction.execute();
            case 2 -> deleteRecipeUIAction.execute();
            case 3 -> getAllRecipesUIAction.execute();
            case 4 -> printRecipeUIAction.execute();
            case 5 -> exitUIAction.execute();
        }
    }
}
