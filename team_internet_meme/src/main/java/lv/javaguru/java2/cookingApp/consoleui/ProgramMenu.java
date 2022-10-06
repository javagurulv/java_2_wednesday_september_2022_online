package lv.javaguru.java2.cookingApp.consoleui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenu {

    private Map<Integer, UIAction> menuNumberToAction;

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToAction = new HashMap<>();
        menuNumberToAction.put(1, findUIAction(uiActions, AddRecipeUIAction.class) );
        menuNumberToAction.put(2, findUIAction(uiActions, DeleteRecipeUIAction.class));
        menuNumberToAction.put(3, findUIAction(uiActions, GetAllRecipesUIAction.class));
        menuNumberToAction.put(4, findUIAction(uiActions, PrintRecipeToConsoleUIAction.class));
        menuNumberToAction.put(5, findUIAction(uiActions, SearchRecipeUIAction.class));
        menuNumberToAction.put(6, findUIAction(uiActions, ExitUIAction.class));
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void print() {
        System.out.println();
        System.out.println("Program menu:");
        System.out.println("1. Add recipe to list");
        System.out.println("2. Delete recipe from list");
        System.out.println("3. Show all recipes in the list");
        System.out.println("4. Print recipe to console");
        System.out.println("5. Search recipes by ingredients");
        System.out.println("6. Exit");
        System.out.println();
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine().replaceAll("[^0-9]", ""));
    }

    public void executeSelectedMenuItem(int userChoice) {
        menuNumberToAction.get(userChoice).execute();
    }
}
