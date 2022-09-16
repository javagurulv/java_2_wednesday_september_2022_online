package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.services.DeleteRecipeService;

import java.util.Scanner;

public class DeleteRecipeUIAction implements UIAction {
    private DeleteRecipeService deleteRecipeService;

    public DeleteRecipeUIAction(DeleteRecipeService deleteRecipeService) {
        this.deleteRecipeService = deleteRecipeService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID of a recipe you wish to delete: ");
        Long id = Long.parseLong(scanner.nextLine());
        DeleteRecipeRequest request = new DeleteRecipeRequest(id);
        deleteRecipeService.execute(request);
        System.out.println("Recipe has been deleted");
    }
}
