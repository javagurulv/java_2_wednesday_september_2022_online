package lv.javaguru.java2.eBooking.console_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenu {

    Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddClientUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, RemoveClientUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, PrintClientUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, SearchClientUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, AddAppointmentUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, RemoveAppointmentUIAction.class));
        menuNumberToUIActionMap.put(7, findUIAction(uiActions, PrintAppointmentUIAction.class));
        menuNumberToUIActionMap.put(8, findUIAction(uiActions, SearchAppointmentUIAction.class));
        menuNumberToUIActionMap.put(9, findUIAction(uiActions, ExitApplicationUIAction.class));

    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printMenu() {
        System.out.println("Appointment Application");
        System.out.println("");
        System.out.println("1. Add a client");
        System.out.println("2. Delete client");
        System.out.println("3. Show client list");
        System.out.println("4. Client search");
        System.out.println("5. Add appointment");
        System.out.println("6. Delete appointment");
        System.out.println("7. Show all appointments");
        System.out.println("8. Appointment search");
        System.out.println("9. Exit");
    }

    public int chooseMenuNumber() {
        System.out.println("Choose menu number to execute");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void executeSelectedMenuNumber (int selectedNumber){
        menuNumberToUIActionMap.get(selectedNumber).execute();
    }
}
