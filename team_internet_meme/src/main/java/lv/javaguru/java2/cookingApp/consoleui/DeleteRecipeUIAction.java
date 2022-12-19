package lv.javaguru.java2.cookingApp.consoleui;

import lv.javaguru.java2.cookingApp.core.dto.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.DeleteRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.DeleteRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteRecipeUIAction implements UIAction {

    @Autowired
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
