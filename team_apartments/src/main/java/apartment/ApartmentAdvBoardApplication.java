package apartment;

import apartment.console_ui.*;
import apartment.core.database.Database;
import apartment.core.database.InMemoryDatabase;
import apartment.core.services.*;

import java.util.*;

public class ApartmentAdvBoardApplication {
//
    private static Database database = new InMemoryDatabase();
    private static UserService userService = new UserService();

    private static AddApartmentValidator addApartmentValidator = new AddApartmentValidator();

    private static SearchApartmentRequestValidator searchApartmentRequestValidator = new SearchApartmentRequestValidator();
    private static AddApartmentService addApartmentService = new AddApartmentService(database, addApartmentValidator);
    private static PrintAllApartmentService printAllApartmentService = new PrintAllApartmentService(database);
    private static RemoveApartmentService removeApartmentService = new RemoveApartmentService(database);

    private static SearchApartmentService searchApartmentService = new SearchApartmentService(database, searchApartmentRequestValidator);
    private static UIAction addApartmentUIAction = new AddApartmentUIUIAction(addApartmentService);
    private static UIAction removeApartmentUIAction = new RemoveApartmentUIUIAction(removeApartmentService);
    private static UIAction printAllApartmentUIAction = new PrintAllApartmentUIUIAction(printAllApartmentService);
    private static UIAction exitUIAction = new ExitUIUIAction();

    private static UIAction searchApartmentUIAction = new SearchApartmentUIAction(searchApartmentService);





//    private static AddNewApartmentAction addNewApartmentAction = new AddNewApartmentAction();
//    private static RemoveApartmentAction removeApartmentAction = new RemoveApartmentAction();
//    private static PrintAllApartmentAction printAllApartmentAction = new PrintAllApartmentAction();
//    private static ExitAction exitAction = new ExitAction();

    public static void main(String[] args) {
        while (true) {
            printProgramMenu();
            int menuNumber = getMenuNumberFromUser();
            executeSelectedMenuItem(menuNumber);
        }
    }

    private static void printProgramMenu(){
        System.out.println("Choose what to do with list: ");
        System.out.println("1. Add apartment to list");
        System.out.println("2. Delete apartment from list");
        System.out.println("3. Show all apartments from the list");
        System.out.println("4. Search the apartment");
        System.out.println("5. Exit");

        System.out.println("");
    }
    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void executeSelectedMenuItem (int selectedMenu){
        switch (selectedMenu){
            case 1: {
                addApartmentUIAction.execute();
                break;
            }
            case 2: {
                removeApartmentUIAction.execute();
                break;
            }
            case 3: {
                printAllApartmentUIAction.execute();
                break;
            }
            case 4: {
                searchApartmentUIAction.execute();
                break;
            }
            case 5: {
                exitUIAction.execute();
                break;
            }
        }
    }

 /*   private static void executeSelectedMenuItem (List<Apartment> apartments, int selectedMenu){

        Map<Integer, UIAction> actionMap = new HashMap<>();
        actionMap.put(1, new AddNewApartmentUIUIAction());
        actionMap.put(2, new RemoveApartmentUIUIAction());
        actionMap.put(3, new PrintAllApartmentUIUIAction());
        actionMap.put(4, new ExitUIUIAction());

        UIAction selectAction = actionMap.get(selectedMenu);
        selectAction.execute(apartments);
    }*/




}
