package lv.javaguru.java2.cookingApp.console_ui;



import lv.javaguru.java2.cookingApp.dependency_injection.ApplicationContext;
import lv.javaguru.java2.cookingApp.dependency_injection.DIApplicationContextBuilder;

import java.util.HashMap;
import java.util.Map;

public class UIActionMap {
    private Map<Integer, UIAction> actionMap;
    private ApplicationContext applicationContext = new DIApplicationContextBuilder().build("lv/javaguru/java2/cookingApp");

    public UIActionMap() {
        actionMap = new HashMap<>();
        actionMap.put(1, applicationContext.getBean(AddRecipeUIAction.class) );
        actionMap.put(2, applicationContext.getBean(DeleteRecipeUIAction.class));
        actionMap.put(3, applicationContext.getBean(GetAllRecipesUIAction.class));
        actionMap.put(4, applicationContext.getBean(PrintRecipeToConsoleUIAction.class));
        actionMap.put(5, applicationContext.getBean(SearchRecipeUIAction.class));
        actionMap.put(6, applicationContext.getBean(ExitUIAction.class));
    }

    public UIAction getAction(int userChoice) {
        return actionMap.get(userChoice);
    }
}
