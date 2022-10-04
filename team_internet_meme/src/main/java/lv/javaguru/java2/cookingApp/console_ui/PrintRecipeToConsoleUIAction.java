package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.core.requests.PrintRecipeToConsoleRequest;
import lv.javaguru.java2.cookingApp.core.responses.PrintRecipeToConsoleResponse;
import lv.javaguru.java2.cookingApp.core.services.PrintRecipeToConsoleService;

import java.util.Scanner;

public class PrintRecipeToConsoleUIAction implements UIAction{
    private PrintRecipeToConsoleService printRecipeToConsoleService;

    public PrintRecipeToConsoleUIAction(PrintRecipeToConsoleService printRecipeToConsoleService) {
        this.printRecipeToConsoleService = printRecipeToConsoleService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID of a recipe to print: ");
        Long id = Long.parseLong(scanner.nextLine());
        PrintRecipeToConsoleRequest request = new PrintRecipeToConsoleRequest(id);
        PrintRecipeToConsoleResponse response = printRecipeToConsoleService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " "
                    + coreError.getMessage()));
        }
    }
}
