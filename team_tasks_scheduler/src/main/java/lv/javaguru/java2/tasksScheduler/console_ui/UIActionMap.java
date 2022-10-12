package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.database.*;
import lv.javaguru.java2.tasksScheduler.services.menu_services.*;
import lv.javaguru.java2.tasksScheduler.services.system.CheckSettingsExistenceService;
import lv.javaguru.java2.tasksScheduler.services.system.GetCurrentUserService;
import lv.javaguru.java2.tasksScheduler.services.system.GetSettingsService;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.*;
import lv.javaguru.java2.tasksScheduler.utils.TestData;

import java.util.HashMap;
import java.util.Map;

public class UIActionMap {

    private SettingsRepository settingsRepository = new InMemorySettingsRepository();
    private UsersRepository usersRepository = new InMemoryUsersRepositoryImpl();
    private TasksRepository tasksRepository = new InMemoryTasksRepositoryImpl();

    private AddSettingsValidator addSettingsValidator = new AddSettingsValidator();
    private AmendSettingsValidator amendSettingsValidator = new AmendSettingsValidator();
    private SettingsLoginValidator settingsLoginValidator = new SettingsLoginValidator();
    private UserRegistrationValidator userInfoValidator = new UserRegistrationValidator();
    private TaskInfoValidator taskInfoValidator = new TaskInfoValidator();
    private UserAmendValidator userAmendInfoValidator = new UserAmendValidator();
    private TaskAmendValidator taskAmendValidator = new TaskAmendValidator();
    private SearchTasksValidator searchTasksValidator = new SearchTasksValidator();
    private LoginValidator loginValidator = new LoginValidator();

    private SessionService sessionService = new SessionService();

    private CheckSettingsExistenceService checkSettingsExistenceService = new CheckSettingsExistenceService(settingsRepository);
    private GetSettingsService getSettingsService = new GetSettingsService(settingsRepository);
    private AddSettingsService addSettingsService = new AddSettingsService(settingsRepository, addSettingsValidator);
    private AmendSettingsService amendSettingsService = new AmendSettingsService(settingsRepository, amendSettingsValidator, sessionService);
    private SettingsLoginService settingsLoginService = new SettingsLoginService(settingsRepository, settingsLoginValidator, sessionService);
    private ExitSettingsService exitSettingsService = new ExitSettingsService(sessionService);
    private GetAllUsersService getAllUsersService = new GetAllUsersService(usersRepository);
    private GetAllUsersNamesService getAllUsersNamesService = new GetAllUsersNamesService(usersRepository);
    private UserRegistrationService userRegistrationService = new UserRegistrationService(usersRepository, userInfoValidator);
    private LoginService loginService = new LoginService(usersRepository, loginValidator, tasksRepository, sessionService);
    private GetOutstandingTasksService getOutstandingTasksService = new GetOutstandingTasksService(tasksRepository, sessionService);
    private GetTasksForTodayService getTasksForTodayService = new GetTasksForTodayService(tasksRepository, sessionService);
    private AddTaskService addTaskService = new AddTaskService(tasksRepository, sessionService, taskInfoValidator);
    private AmendTaskService amendTaskService = new AmendTaskService(tasksRepository, taskAmendValidator);
    private DeleteTaskService deleteTaskService = new DeleteTaskService(tasksRepository);
    private DeleteCurrentUserService deleteCurrentUserService = new DeleteCurrentUserService(usersRepository, tasksRepository, sessionService);
    private AmendCurrentUserService amendCurrentUserService = new AmendCurrentUserService(usersRepository, sessionService, userAmendInfoValidator);
    private GetCurrentUserService getCurrentUserService = new GetCurrentUserService(usersRepository, sessionService);
    private LogoutService logoutService = new LogoutService(sessionService);
    private ExitService exitService = new ExitService();
    private SearchTasksService searchTasksService = new SearchTasksService(tasksRepository, searchTasksValidator , sessionService);

    private Map<Integer, UIAction> actionMap;

    //TODO remove tests user and tasks
    TestData testData = new TestData(userRegistrationService, addTaskService,
            loginService, logoutService);


    public UIActionMap() {
        this.actionMap = new HashMap<>();
        actionMap.put(0, new AddSettingsUIAction(checkSettingsExistenceService, addSettingsService));
        actionMap.put(1, new GetAllUsersNamesUIAction(getAllUsersNamesService));
        actionMap.put(2, new LoginUIAction(loginService, getTasksForTodayService));
        actionMap.put(3, new UserRegistrationUIAction(userRegistrationService));
        actionMap.put(4, new SettingsLoginUIAction(checkSettingsExistenceService, settingsLoginService, sessionService));
        actionMap.put(5, new ExitUIAction(exitService));
        actionMap.put(6, new GetOutstandingTasksUIAction(getOutstandingTasksService));
        actionMap.put(7, new GetTasksForTodayUIAction(getTasksForTodayService));
        actionMap.put(8, new AddTaskUIAction(addTaskService));
        actionMap.put(9, new AmendTaskUIAction(amendTaskService, getOutstandingTasksService));
        actionMap.put(10, new SearchTasksUIAction(searchTasksService));
        actionMap.put(11, new DeleteTaskUIAction(deleteTaskService, getOutstandingTasksService));
        actionMap.put(12, new AmendCurrentUserUIAction(amendCurrentUserService, getCurrentUserService));
        actionMap.put(13, new DeleteCurrentUserUIAction(deleteCurrentUserService));
        actionMap.put(14, new LogoutUIAction(logoutService));
        actionMap.put(15, new GetAllUsersUIAction(getAllUsersService));
        actionMap.put(16, new AmendSettingsUIAction(amendSettingsService, getSettingsService, sessionService));
        actionMap.put(17, new ExitSettingsUIAction(exitSettingsService));

    }

    public UIAction getAction(int userChoice) {
        return actionMap.get(userChoice);
    }

}
