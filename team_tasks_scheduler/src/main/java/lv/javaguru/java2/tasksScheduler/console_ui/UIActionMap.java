package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.config.TaskSchedulerConfig;
import lv.javaguru.java2.tasksScheduler.services.scheduled_jobs.TasksCleanupService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lv.javaguru.java2.tasksScheduler.utils.TestData;

import java.util.HashMap;
import java.util.Map;

public class UIActionMap {

    @Value("${need.test.data.users}")
    private boolean isTestUsersNeeded;
    @Value("${need.test.data.tasks}")
    private boolean isTestTasksNeeded;
    @Value("${need.test.data.settings}")
    private boolean isTestSettingsNeeded;
    private static ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);

    private Map<Integer, UIAction> actionMap;
    public UIActionMap() {

        this.actionMap = new HashMap<>();
        actionMap.put(0, applicationContext.getBean(AddSettingsUIAction.class));
        actionMap.put(1, applicationContext.getBean(GetAllUsersNamesUIAction.class));
        actionMap.put(2, applicationContext.getBean(LoginUIAction.class));
        actionMap.put(3, applicationContext.getBean(UserRegistrationUIAction.class));
        actionMap.put(4, applicationContext.getBean(SettingsLoginUIAction.class));
        actionMap.put(5, applicationContext.getBean(ExitUIAction.class));
        actionMap.put(6, applicationContext.getBean(GetOutstandingTasksUIAction.class));
        actionMap.put(7, applicationContext.getBean(GetTasksForTodayUIAction.class));
        actionMap.put(8, applicationContext.getBean(AddTaskUIAction.class));
        actionMap.put(9, applicationContext.getBean(AmendTaskUIAction.class));
        actionMap.put(10, applicationContext.getBean(SearchTasksUIAction.class));
        actionMap.put(11, applicationContext.getBean(DeleteTaskUIAction.class));
        actionMap.put(12, applicationContext.getBean(AmendCurrentUserUIAction.class));
        actionMap.put(13, applicationContext.getBean(DeleteCurrentUserUIAction.class));
        actionMap.put(14, applicationContext.getBean(LogoutUIAction.class));
        actionMap.put(15, applicationContext.getBean(GetAllUsersUIAction.class));
        actionMap.put(16, applicationContext.getBean(AmendSettingsUIAction.class));
        actionMap.put(17, applicationContext.getBean(ExitSettingsUIAction.class));

        //TODO remove tests user and tasks
        TestData testData = applicationContext.getBean(TestData.class);
        if (isTestSettingsNeeded)
            testData.createTestSettings();
        if (isTestUsersNeeded)
            testData.createTestUsers();
        if (isTestTasksNeeded)
            testData.createTestTasks();

        TasksCleanupService cleanupService = applicationContext.getBean(TasksCleanupService.class);
        cleanupService.start();

    }

    public UIAction getAction(int userChoice) {
        return actionMap.get(userChoice);
    }

}
