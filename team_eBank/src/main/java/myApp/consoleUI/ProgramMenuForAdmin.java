package myApp.consoleUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenuForAdmin {
    private Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public ProgramMenuForAdmin(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, GetAllBankAccountsUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, GetAllUsersUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, AddBankAccountUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, RemoveBankAccountUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, SearchBankAccountUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, SwitchUserUIAction.class));
        menuNumberToUIActionMap.put(7, findUIAction(uiActions, ExitUIAction.class));
    }

    public int userChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu number: ");
        return scanner.nextInt();
    }

    public void printInformationForAdmin() {
        System.out.println();
        System.out.println("Admin menu: ");
        System.out.println("1 - Get all bank accounts");
        System.out.println("2 - Get all users");
        System.out.println("3 - Add bank account");
        System.out.println("4 - Remove bank account");
        System.out.println("5 - Search bank account");
        System.out.println("6 - Switch user");
        System.out.println("7 - Exit");
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }
    public void executeSelectedMenuItem(int selectedMenu) {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }
}
