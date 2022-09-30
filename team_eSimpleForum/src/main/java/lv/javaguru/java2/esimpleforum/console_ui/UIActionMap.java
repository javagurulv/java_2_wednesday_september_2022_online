package lv.javaguru.java2.esimpleforum.console_ui;

import lv.javaguru.java2.esimpleforum.service.PostManagerService;

import java.util.HashMap;
import java.util.Map;

public class UIActionMap {

    private Map<Integer, UIAction> actionMap;

    public UIActionMap(PostManagerService postManagerService) {
        this.actionMap = new HashMap<>();
        actionMap.put(1, new AddPostUIAction(postManagerService));
        actionMap.put(2, new RemovePostUIAction(postManagerService));
        actionMap.put(3, new GetAllPostsUIAction(postManagerService));
        actionMap.put(4, new ExitUIAction(postManagerService));
    }

    public UIAction getAction(int userChoice) {

        return actionMap.get(userChoice);
    }

}
