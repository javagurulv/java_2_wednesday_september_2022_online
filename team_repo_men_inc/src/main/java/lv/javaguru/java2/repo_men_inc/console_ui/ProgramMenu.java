package lv.javaguru.java2.repo_men_inc.console_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenu {

    private Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddDebtorUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, AddHarvestedItemUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, RemoveDebtorUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, PrintDebtorListUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, SearchDebtorUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, ExitUIAction.class));
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printMenu() {
        System.out.println("========================================================");
        System.out.println("Program menu:");
        System.out.println("1. Add new debtor to list");
        System.out.println("2. Add harvested item to debtors list");
        System.out.println("3. Delete debtor from list");
        System.out.println("4. Show all debtors in the list");
        System.out.println("5. Search Debtors");
        System.out.println("6. Exit");
        System.out.println("========================================================");
    }

    public int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu item number to execute:");
        return Integer.parseInt(scanner.nextLine());
    }

    public void executeUsersChoice(int userChoice) {
        menuNumberToUIActionMap.get(userChoice).execute();
    }
}
