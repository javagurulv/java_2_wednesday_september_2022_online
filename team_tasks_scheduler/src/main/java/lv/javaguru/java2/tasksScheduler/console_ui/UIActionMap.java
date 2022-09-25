package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.database.InMemoryTasksRepositoryImpl;
import lv.javaguru.java2.tasksScheduler.database.InMemoryUsersRepositoryImpl;
import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.services.AddTaskService;
import lv.javaguru.java2.tasksScheduler.services.GetAllUsersService;
import lv.javaguru.java2.tasksScheduler.services.LoginService;
import lv.javaguru.java2.tasksScheduler.services.UserRegistrationService;

import java.util.HashMap;
import java.util.Map;

public class UIActionMap {

    private UsersRepository usersRepository = new InMemoryUsersRepositoryImpl();
    private TasksRepository tasksRepository = new InMemoryTasksRepositoryImpl();

    private GetAllUsersService getAllUsersService = new GetAllUsersService(usersRepository);
    private UserRegistrationService userRegistrationService = new UserRegistrationService(usersRepository);
    private LoginService loginService = new LoginService(usersRepository);

    private AddTaskService addTaskService = new AddTaskService(tasksRepository);
    private Map<Integer, UIAction> actionMap;

    public UIActionMap() {
        this.actionMap = new HashMap<>();
        actionMap.put(1, new GetAllUsersUIAction(getAllUsersService));
        actionMap.put(2, new LoginUIAction(loginService));
        actionMap.put(3, new UserRegistrationUIAction(userRegistrationService));
        actionMap.put(4, new ExitUIAction());
        actionMap.put(5, null);
        actionMap.put(6, null);
        actionMap.put(7, null);
        actionMap.put(8, null);
        actionMap.put(9, null);
        actionMap.put(10, null);
        actionMap.put(11, new LogoutUIAction());
    }

    public UIAction getAction(int userChoice) {
        return actionMap.get(userChoice);
    }

}
