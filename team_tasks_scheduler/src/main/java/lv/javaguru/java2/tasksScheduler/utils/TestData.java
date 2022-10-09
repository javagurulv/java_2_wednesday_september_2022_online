package lv.javaguru.java2.tasksScheduler.utils;

import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.responses.AddTaskResponse;
import lv.javaguru.java2.tasksScheduler.responses.LoginResponse;
import lv.javaguru.java2.tasksScheduler.responses.LogoutResponse;
import lv.javaguru.java2.tasksScheduler.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.AddTaskService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.LoginService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.LogoutService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.UserRegistrationService;

import java.time.LocalDateTime;

public class TestData {
    private UserRegistrationService userService;
    private AddTaskService taskService;
    private LoginService loginService;
    private LogoutService logoutService;

    public TestData(UserRegistrationService userService, AddTaskService taskService,
                    LoginService login, LogoutService logout) {
        this.userService = userService;
        this.taskService = taskService;
        this.loginService = login;
        this.logoutService = logout;

        createAdmin();
        createUsers();
        createTasks();
    }

    private void createAdmin() {

    }
    private void createUsers() {
        UserRegistrationRequest request = new UserRegistrationRequest("1111","1111",
                "a@b.c", false);
        UserRegistrationResponse response = userService.execute(request);

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
                LocalDateTime.of(2022,11,12,9,5),
                LocalDateTime.of(2022,11,10,17,55));
        response = taskService.execute(request);
        request = new AddTaskRequest("appointment to dentist", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,12,1,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("get car from a workshop", 0,
                LocalDateTime.of(2022,11,12,9,5),
                LocalDateTime.of(2022,11,10,17,55));
        response = taskService.execute(request);
        request = new AddTaskRequest("check joga classes", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,12,1,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("birth day of brother's son", 0,
                LocalDateTime.of(2022,11,12,9,5),
                LocalDateTime.of(2022,11,10,17,55));
        response = taskService.execute(request);
        request = new AddTaskRequest("buy flower for colleague", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,12,1,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("first day of the month", 0,
                LocalDateTime.of(2022,11,12,9,5),
                LocalDateTime.of(2022,11,10,17,55));
        response = taskService.execute(request);
        request = new AddTaskRequest("valentine's day", 2,
                LocalDateTime.of(2022,12,30,8,10),
                LocalDateTime.of(2022,12,1,20,40));
        response = taskService.execute(request);
        request = new AddTaskRequest("take my medication; every day", 0,
                LocalDateTime.of(2022,11,12,9,5),
                LocalDateTime.of(2022,11,10,17,55));
        response = taskService.execute(request);
        request = new AddTaskRequest("download some movie", 2,
                LocalDateTime.of(2022,12,30,8,10),
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

}
