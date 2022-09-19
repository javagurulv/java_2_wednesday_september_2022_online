package lv.javaguru.java2.eSimpleForum;

import lv.javaguru.java2.eSimpleForum.console_ui.*;
import lv.javaguru.java2.eSimpleForum.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.eSimpleForum.service.PostManagerService;

import java.util.*;

public class ForumApplication {

    static PostManagerService postManagerService = new PostManagerService(
            new InMemoryDatabaseImpl()
    );

    public static void main(String[] args) {
        List<Post> posts = new ArrayList<>();

        while (true) {
            printMenu();
            int userChoice = getUserChoice();
            executedSelectedMenuItem(userChoice);
        }
    }

    private static void executedSelectedMenuItem(int userChoice) {
        Map<Integer, UIAction> uiActionMap = new HashMap<>();
        uiActionMap.put(1, new AddPostUIAction(postManagerService));
        uiActionMap.put(2, new RemovePostUIAction(postManagerService));
        uiActionMap.put(3, new GetAllPostsUiAction(postManagerService));
        uiActionMap.put(4, new ExitUIAction(postManagerService));

        uiActionMap.get(userChoice).execute();
    }

    private static int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        int userChoice = Integer.parseInt(scanner.nextLine());
        return userChoice;
    }

    private static void printMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Add posts to list");
        System.out.println("2. Delete post from list");
        System.out.println("3. Show all posts in the list");
        System.out.println("4. Exit");
    }
}
