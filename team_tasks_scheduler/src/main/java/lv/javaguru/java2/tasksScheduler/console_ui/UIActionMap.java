package lv.javaguru.java2.tasksScheduler.console_ui;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UIActionMap {

    private Map<Integer, UIAction> actionMap;

    @Autowired
    public UIActionMap(List<UIAction> uiActions) {
        actionMap = new HashMap<>();
        actionMap.put(0, findUIAction(uiActions, AddSettingsUIAction.class));
        actionMap.put(1, findUIAction(uiActions, GetUsersNamesUIAction.class));
        actionMap.put(2, findUIAction(uiActions, LoginUIAction.class));
        actionMap.put(3, findUIAction(uiActions, UserRegistrationUIAction.class));
        actionMap.put(4, findUIAction(uiActions, SettingsLoginUIAction.class));
        actionMap.put(5, findUIAction(uiActions, ExitUIAction.class));
        actionMap.put(6, findUIAction(uiActions, GetOutstandingTasksUIAction.class));
        actionMap.put(7, findUIAction(uiActions, GetTasksForTodayUIAction.class));
        actionMap.put(8, findUIAction(uiActions, AddTaskUIAction.class));
        actionMap.put(9, findUIAction(uiActions, AmendTaskUIAction.class));
        actionMap.put(10, findUIAction(uiActions, SearchTasksUIAction.class));
        actionMap.put(11, findUIAction(uiActions, DeleteTaskUIAction.class));
        actionMap.put(12, findUIAction(uiActions, AmendCurrentUserUIAction.class));
        actionMap.put(13, findUIAction(uiActions, DeleteCurrentUserUIAction.class));
        actionMap.put(14, findUIAction(uiActions, LogoutUIAction.class));
        actionMap.put(15, findUIAction(uiActions, GetUsersUIAction.class));
        actionMap.put(16, findUIAction(uiActions, AmendSettingsUIAction.class));
        actionMap.put(17, findUIAction(uiActions, ExitSettingsUIAction.class));
     }

    public UIAction getAction(int userChoice) {
        return actionMap.get(userChoice);
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

}
