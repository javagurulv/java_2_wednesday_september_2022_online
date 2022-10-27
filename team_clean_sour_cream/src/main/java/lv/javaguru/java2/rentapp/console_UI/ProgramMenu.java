package lv.javaguru.java2.rentapp.console_UI;

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
        this.menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddVehicleUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, DeleteVehicleByPlateNumberUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, ShowAllVehiclesUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, SearchVehicleUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, RentVehicleUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, ExitProgramUIAction.class));
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("Program menu:");
        System.out.println("1. Add vehicle to list");
        System.out.println("2. Delete vehicle from list by plate number");
        System.out.println("3. Show all vehicles in the list");
        System.out.println("4. Search vehicles in the list");
        System.out.println("5. Make reservation");
        System.out.println("6. Exit");
        System.out.println();
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine().replaceAll("[^0-9]", ""));
    }

    public void executeSelectedMenuItem(int userChoice) {
        menuNumberToUIActionMap.get(userChoice).execute();
        System.out.println();
    }

    public Integer getMenuNumberToUIActionMapSize() {
        return menuNumberToUIActionMap.size();
    }
}

