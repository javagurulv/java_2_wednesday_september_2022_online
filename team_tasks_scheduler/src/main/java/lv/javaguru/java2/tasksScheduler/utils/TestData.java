package lv.javaguru.java2.tasksScheduler.utils;



import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.*;
import lv.javaguru.java2.tasksScheduler.responses.*;
import lv.javaguru.java2.tasksScheduler.services.menu_services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class TestData {
    @Value("${need.test.data.users}")
    private boolean isTestUsersNeeded;
    @Value("${need.test.data.tasks}")
    private boolean isTestTasksNeeded;
    @Value("${need.test.data.settings}")
    private boolean isTestSettingsNeeded;
    @Autowired private AddSettingsService addSettingsService;
    @Autowired private UserRegistrationService userService;
    @Autowired private AddTaskService taskService;
    @Autowired private LoginService loginService;
    @Autowired private LogoutService logoutService;

    public void createTestUsers() {
        if (isTestUsersNeeded) {
            createUsers();
        }
    }
    public void createTestTasks() {
        if (isTestTasksNeeded) {
            createTasks();
        }
    }
    public void createTestSettings() {
        if (isTestSettingsNeeded){
            createSettings();
        }
    }

    private void createSettings() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "olegsktest@gmail.com",
                "glzblubwocovtifc", "smtp.gmail.com", "465", "ssl");
        AddSettingsResponse response = addSettingsService.execute(request);
    }

    private void createUsers() {
        UserRegistrationRequest request = new UserRegistrationRequest("1111","1111",
                "a@b.c", false);
        UserRegistrationResponse response = userService.execute(request);

        request = new UserRegistrationRequest("2222","2222",
                "t@t.t", false);
        response = userService.execute(request);

        request = new UserRegistrationRequest("John Doe","2222",
                "c@b.a", false);
        response = userService.execute(request);

        request = new UserRegistrationRequest("Jane Doe","3333",
                "qwery@abc.hh", false);
        response = userService.execute(request);

        request = new UserRegistrationRequest("Thor Odinson","4444",
                "abc123@in.xz", false);
        response = userService.execute(request);

        request = new UserRegistrationRequest("123soldier","5555",
                "must@year.ac", false);
        response = userService.execute(request);

        request = new UserRegistrationRequest("007Bond","6666",
                "james@adam.al", false);
        response = userService.execute(request);

        request = new UserRegistrationRequest("Nagibator666","7777",
                "bender6@box.at", false);
        response = userService.execute(request);

        request = new UserRegistrationRequest("Star Destroyer","8888",
                "creater@vava.one", false);
        response = userService.execute(request);

        request = new UserRegistrationRequest("Darth Vader","9999",
                "anakin@walker.sky", false);
        response = userService.execute(request);
    }

    private void createTasks() {

        LoginRequest loginRequest = new LoginRequest("1111","1111");
        LoginResponse loginResponse = loginService.execute(loginRequest);

        AddTaskRequest request = new AddTaskRequest("first task task", 0,
                LocalDateTime.of(2022,11,12,9,5),
                LocalDateTime.of(2022,11,10,17,55));
        AddTaskResponse response = taskService.execute(request);
        request = new AddTaskRequest("2nd task task", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,12,1,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("3rd task task task", 0,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2022,12,1,10,30));
        response = taskService.execute(request);
        request = new AddTaskRequest("appointment to dentist", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,12,1,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("get car from a workshop", 0,
                LocalDateTime.of(2023,2,12,9,5),
                LocalDateTime.of(2023,1,10,17,55));
        response = taskService.execute(request);
        request = new AddTaskRequest("check joga classes", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,12,1,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("birth day of brother's son", 0,
                LocalDateTime.of(2023,11,12,9,5),
                LocalDateTime.of(2022,11,10,17,55));
        response = taskService.execute(request);
        request = new AddTaskRequest("buy flower for colleague", 2,
                LocalDateTime.of(2024,12,30,8,10),
                LocalDateTime.of(2022,12,1,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("first day of the month", 0,
                LocalDateTime.of(2023,12,12,9,5),
                LocalDateTime.of(2022,11,10,17,55));
        response = taskService.execute(request);
        request = new AddTaskRequest("valentine's day", 2,
                LocalDateTime.of(2023,5,30,8,10),
                LocalDateTime.of(2023,4,1,20,40));
        response = taskService.execute(request);
        //--------------------------
        request = new AddTaskRequest("take my medication; every day", 0,
                LocalDateTime.of(2023,3,12,9,5),
                LocalDateTime.of(2023,2,10,17,55));
        response = taskService.execute(request);
        //--------------------
        request = new AddTaskRequest("download some movies", 0,
                LocalDateTime.of(2023,3,12,9,5),
                LocalDateTime.of(2023,2,10,17,55));
        response = taskService.execute(request);
        checkResponseForErrors(response);
        //--------------------
        request = new AddTaskRequest("buy chocolate", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,11,10,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("take a shower", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,11,20,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("tomorrow has to go to work:(", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,12,22,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("after a month is vacation", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,12,29,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("buy groceries", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,12,10,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("pay bills and mortgage", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,11,25,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("cinema in the evening", 2,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2022,12,1,20,40));
        response = taskService.execute(request);




        LogoutRequest logoutRequest = new LogoutRequest();
        LogoutResponse logoutResponse = logoutService.execute(logoutRequest);


        loginRequest = new LoginRequest("2222","2222");
        loginResponse = loginService.execute(loginRequest);

        request = new AddTaskRequest("first task task", 0,
                LocalDateTime.of(2022,11,12,9,5),
                LocalDateTime.of(2022,11,10,17,55));
        response = taskService.execute(request);
        request = new AddTaskRequest("2nd task task", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,12,1,20,40));
        response = taskService.execute(request);

        logoutRequest = new LogoutRequest();
        logoutResponse = logoutService.execute(logoutRequest);

    }
    private boolean checkResponseForErrors(AddTaskResponse response) {
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return true;
        }
        return false;
    }

}
