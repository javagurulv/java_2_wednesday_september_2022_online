package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.services.PrintRecipeToConsoleService;

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
        printRecipeToConsoleService.execute(id);
    }
}
