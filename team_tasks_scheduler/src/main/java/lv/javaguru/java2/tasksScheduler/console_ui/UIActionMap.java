package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.database.InMemoryTasksRepositoryImpl;
import lv.javaguru.java2.tasksScheduler.database.InMemoryUsersRepositoryImpl;
import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.services.*;

import java.util.HashMap;
import java.util.Map;

public class UIActionMap {

    private UsersRepository usersRepository = new InMemoryUsersRepositoryImpl();
    private TasksRepository tasksRepository = new InMemoryTasksRepositoryImpl();
    private UserRegistrationValidator userInfoValidator = new UserRegistrationValidator();
    private TaskInfoValidator taskInfoValidator = new TaskInfoValidator();
    private UserAmendValidator userAmendInfoValidator = new UserAmendValidator();
    private SessionService sessionService = new SessionService();

    private GetAllUsersService getAllUsersService = new GetAllUsersService(usersRepository);
    private GetAllUsersNamesService getAllUsersNamesService = new GetAllUsersNamesService(usersRepository);
    private UserRegistrationService userRegistrationService = new UserRegistrationService(usersRepository, userInfoValidator);
    private LoginService loginService = new LoginService(usersRepository, tasksRepository, sessionService);
    private GetOutstandingTasksService getOutstandingTasksService = new GetOutstandingTasksService(tasksRepository, sessionService);
    private GetTasksForTodayService getTasksForTodayService = new GetTasksForTodayService(tasksRepository, sessionService);
    private AddTaskService addTaskService = new AddTaskService(tasksRepository, sessionService, taskInfoValidator);
    private AmendTaskService amendTaskService = new AmendTaskService(tasksRepository);
    private DeleteTaskService deleteTaskService = new DeleteTaskService(tasksRepository);
    private DeleteCurrentUserService deleteCurrentUserService = new DeleteCurrentUserService(usersRepository, tasksRepository, sessionService);
    private AmendCurrentUserService amendCurrentUserService = new AmendCurrentUserService(usersRepository, sessionService, userAmendInfoValidator);
    private GetCurrentUserService getCurrentUserService = new GetCurrentUserService(usersRepository, sessionService);
    private LogoutService logoutService = new LogoutService(sessionService);

    private Map<Integer, UIAction> actionMap;

    public UIActionMap() {
        this.actionMap = new HashMap<>();
//        actionMap.put(1, new GetAllUsersUIAction(getAllUsersService));
        actionMap.put(1, new GetAllUsersNamesUIAction(getAllUsersNamesService));
        actionMap.put(2, new LoginUIAction(loginService, getTasksForTodayService));
        actionMap.put(3, new UserRegistrationUIAction(userRegistrationService));
        actionMap.put(4, new ExitUIAction());
        actionMap.put(5, new GetOutstandingTasksUIAction(getOutstandingTasksService));
        actionMap.put(6, new GetTasksForTodayUIAction(getTasksForTodayService));
        actionMap.put(7, new AddTaskUIAction(addTaskService));
        actionMap.put(8, new AmendTaskUIAction(amendTaskService, getOutstandingTasksService));
        actionMap.put(9, new DeleteTaskUIAction(deleteTaskService, getOutstandingTasksService));
        actionMap.put(10, new AmendCurrentUserUIAction(amendCurrentUserService, getCurrentUserService));
        actionMap.put(11, new DeleteCurrentUserUIAction(deleteCurrentUserService));
        actionMap.put(12, new LogoutUIAction(logoutService));
    }

    public UIAction getAction(int userChoice) {
        return actionMap.get(userChoice);
    }

}
