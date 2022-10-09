package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.core.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.DeleteRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.DeleteRecipeService;
import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;
import lv.javaguru.java2.cookingApp.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class DeleteRecipeUIAction implements UIAction {

   @DIDependency
   private DeleteRecipeService deleteRecipeService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID of a recipe you wish to delete: ");
        Long id = Long.parseLong(scanner.nextLine());
        DeleteRecipeRequest request = new DeleteRecipeRequest(id);
        DeleteRecipeResponse response = deleteRecipeService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error " + coreError.getField() + " "
                    + coreError.getMessage()));
        } else if (!response.isRecipeDeleted()) {
            System.out.println("Recipe has not been found");
        } else {
            System.out.println("Recipe has been deleted");
        }

    }
}