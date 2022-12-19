package lv.javaguru.java2.tasksScheduler.acceptancetests;

import lv.javaguru.java2.tasksScheduler.config.TaskSchedulerCoreConfig;
import lv.javaguru.java2.tasksScheduler.core.requests.*;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.core.responses.GetUsersResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.SearchTasksResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.*;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TaskSchedulerCoreConfig.class})
@Sql({"/schema.sql"})
public class AcceptanceTest2 {

    @Autowired private AddSettingsService addSettingsService;
    @Autowired private UserRegistrationService userService;
    @Autowired private AddTaskService taskService;
    @Autowired private LoginService loginService;
    @Autowired private LogoutService logoutService;
    @Autowired private GetUsersService getUsersService;
    @Autowired private GetOutstandingTasksService getOutstandingTasksService;
    @Autowired private SearchTasksService searchTasksService;


    @Test
    public void searchUsers() {
        UserRegistrationRequest registrationRequest;
        GetUsersRequest getRequest;
        GetUsersResponse getResponse;

        registrationRequest = new UserRegistrationRequest("user1","1111",
                "user1@b.c", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user2","2222",
                "user2@t.t", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user3","3333",
                "user3@b.a", false);
        userService.execute(registrationRequest);


        getRequest = new GetUsersRequest("user", null, null, null);
        getResponse  = getUsersService.execute(getRequest, MenuType.ADMIN);

        assertEquals(3, getResponse.getUsers().size());
        assertEquals("user1", getResponse.getUsers().get(0).getUsername());
        assertEquals(getResponse.getUsers().get(0).getEmail(), "user1@b.c");
        assertEquals(getResponse.getUsers().get(1).getUsername(), "user2");
        assertEquals(getResponse.getUsers().get(1).getEmail(), "user2@t.t");
        assertEquals(getResponse.getUsers().get(2).getUsername(), "user3");
        assertEquals(getResponse.getUsers().get(2).getEmail(), "user3@b.a");
    }

    @Test
    public void searchUsersOrderingDescending() {
        UserRegistrationRequest registrationRequest;
        GetUsersRequest getRequest;
        GetUsersResponse getResponse;
        Ordering order;

        registrationRequest = new UserRegistrationRequest("user1","1111",
                "user1@b.c", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user2","2222",
                "user2@t.t", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user3","3333",
                "user3@b.a", false);
        userService.execute(registrationRequest);

        order = new Ordering("username", "DESCENDING");
        getRequest = new GetUsersRequest("user", null, order, null);
        getResponse  = getUsersService.execute(getRequest, MenuType.ADMIN);

        assertEquals(3, getResponse.getUsers().size());
        assertEquals(getResponse.getUsers().get(0).getUsername(), "user3");
        assertEquals(getResponse.getUsers().get(0).getEmail(), "user3@b.a");
        assertEquals("user2", getResponse.getUsers().get(1).getUsername());
        assertEquals(getResponse.getUsers().get(1).getEmail(), "user2@t.t");
        assertEquals(getResponse.getUsers().get(2).getUsername(), "user1");
        assertEquals(getResponse.getUsers().get(2).getEmail(), "user1@b.c");


        order = new Ordering("email", "DESCENDING");
        getRequest = new GetUsersRequest(null, "se", order, null);
        getResponse  = getUsersService.execute(getRequest, MenuType.ADMIN);

        assertEquals(3, getResponse.getUsers().size());
        assertEquals(getResponse.getUsers().get(0).getUsername(), "user3");
        assertEquals(getResponse.getUsers().get(0).getEmail(), "user3@b.a");
        assertEquals(getResponse.getUsers().get(1).getUsername(), "user2");
        assertEquals("user2@t.t", getResponse.getUsers().get(1).getEmail());
        assertEquals(getResponse.getUsers().get(2).getUsername(), "user1");
        assertEquals(getResponse.getUsers().get(2).getEmail(), "user1@b.c");


        order = new Ordering("email", "DESCENDING");
        getRequest = new GetUsersRequest("u", "b", order, null);
        getResponse  = getUsersService.execute(getRequest, MenuType.ADMIN);

        assertEquals(2, getResponse.getUsers().size());
        assertEquals(getResponse.getUsers().get(0).getUsername(), "user3");
        assertEquals(getResponse.getUsers().get(0).getEmail(), "user3@b.a");
        assertEquals("user1", getResponse.getUsers().get(1).getUsername());
        assertEquals(getResponse.getUsers().get(1).getEmail(), "user1@b.c");
    }

    @Test
    public void searchUsersOrderingAscending() {
        UserRegistrationRequest registrationRequest;
        GetUsersRequest getRequest;
        GetUsersResponse getResponse;
        Ordering order;

        registrationRequest = new UserRegistrationRequest("user1","1111",
                "user1@b.c", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user2","2222",
                "user2@t.t", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user3","3333",
                "user3@b.a", false);
        userService.execute(registrationRequest);

        order = new Ordering("username", "ASCENDING");
        getRequest = new GetUsersRequest("user", null, order, null);
        getResponse  = getUsersService.execute(getRequest, MenuType.ADMIN);

        assertEquals(3, getResponse.getUsers().size());
        assertEquals(getResponse.getUsers().get(0).getUsername(), "user1");
        assertEquals(getResponse.getUsers().get(0).getEmail(), "user1@b.c");
        assertEquals(getResponse.getUsers().get(1).getUsername(), "user2");
        assertEquals("user2@t.t", getResponse.getUsers().get(1).getEmail());
        assertEquals(getResponse.getUsers().get(2).getUsername(), "user3");
        assertEquals(getResponse.getUsers().get(2).getEmail(), "user3@b.a");


        order = new Ordering("email", "ASCENDING");
        getRequest = new GetUsersRequest(null, "se", order, null);
        getResponse  = getUsersService.execute(getRequest, MenuType.ADMIN);

        assertEquals(3, getResponse.getUsers().size());
        assertEquals(getResponse.getUsers().get(0).getUsername(), "user1");
        assertEquals(getResponse.getUsers().get(0).getEmail(), "user1@b.c");
        assertEquals("user2", getResponse.getUsers().get(1).getUsername());
        assertEquals(getResponse.getUsers().get(1).getEmail(), "user2@t.t");
        assertEquals(getResponse.getUsers().get(2).getUsername(), "user3");
        assertEquals(getResponse.getUsers().get(2).getEmail(), "user3@b.a");


        order = new Ordering("email", "ASCENDING");
        getRequest = new GetUsersRequest("u", "b", order, null);
        getResponse  = getUsersService.execute(getRequest, MenuType.ADMIN);

        assertEquals(2, getResponse.getUsers().size());
        assertEquals(getResponse.getUsers().get(0).getUsername(), "user1");
        assertEquals("user1@b.c", getResponse.getUsers().get(0).getEmail());
        assertEquals(getResponse.getUsers().get(1).getUsername(), "user3");
        assertEquals("user3@b.a", getResponse.getUsers().get(1).getEmail());
    }

    @Test
    public void searchUsersOrderingPaging() {
        UserRegistrationRequest registrationRequest;
        GetUsersRequest getRequest;
        GetUsersResponse getResponse;
        Ordering order;
        Paging pages;

        registrationRequest = new UserRegistrationRequest("user1","1111",
                "user1@b.c", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user2","2222",
                "user2@t.t", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user3","3333",
                "user3@b.a", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user4","4444",
                "user4@t.a", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user5","5555",
                "user5@c.a", false);
        userService.execute(registrationRequest);

        order = new Ordering("username", "DESCENDING");
        pages = new Paging(1, 4);
        getRequest = new GetUsersRequest("user", null, order, pages);
        getResponse  = getUsersService.execute(getRequest, MenuType.ADMIN);

        assertEquals(4, getResponse.getUsers().size());
        assertEquals(getResponse.getUsers().get(0).getUsername(), "user5");
        assertEquals(getResponse.getUsers().get(0).getEmail(), "user5@c.a");
        assertEquals(getResponse.getUsers().get(1).getUsername(), "user4");
        assertEquals(getResponse.getUsers().get(1).getEmail(), "user4@t.a");
        assertEquals(getResponse.getUsers().get(2).getUsername(), "user3");
        assertEquals(getResponse.getUsers().get(2).getEmail(), "user3@b.a");

    }

    @Test
    public void searchTasksOrderingDescending() {
        AddTaskRequest request;
        UserRegistrationRequest registrationRequest;
        SearchTasksRequest getTasksRequest;
        SearchTasksResponse getTasksResponse;
        Ordering order;

        registrationRequest= new UserRegistrationRequest("1111",
                "1111","a@b.c", false);
        userService.execute(registrationRequest);


        loginService.execute(new LoginRequest("1111","1111"));

        request = new AddTaskRequest("first task task", 0,
                LocalDateTime.of(2023,9,12,9,4),
                LocalDateTime.of(2023,11,12,9,5));
        taskService.execute(request);
        request = new AddTaskRequest("2nd task task", 2,
                LocalDateTime.of(2023,11,30,8,10),
                LocalDateTime.of(2023,12,1,20,40));
        taskService.execute(request);
        request = new AddTaskRequest("3rd task task task", 0,
                LocalDateTime.of(2023,7,30,8,10),
                LocalDateTime.of(2023,12,30,8,10));
        taskService.execute(request);
        request = new AddTaskRequest("check joga classes", 2,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2027,12,1,20,40));
        taskService.execute(request);

        logoutService.execute(new LogoutRequest());


        loginService.execute(new LoginRequest("1111","1111"));
        order = new Ordering("description", "descending");
        getTasksRequest = new SearchTasksRequest("tas", order);
        getTasksResponse = searchTasksService.execute(getTasksRequest);
        logoutService.execute(new LogoutRequest());

        assertEquals(3, getTasksResponse.getTasks().size());
        assertEquals("first task task", getTasksResponse.getTasks().get(0).getDescription());
        assertEquals("3rd task task task", getTasksResponse.getTasks().get(1).getDescription());
        assertEquals("2nd task task", getTasksResponse.getTasks().get(2).getDescription());



        loginService.execute(new LoginRequest("1111","1111"));
        order = new Ordering("end date", "descending");
        getTasksRequest = new SearchTasksRequest("20", order);
        getTasksResponse = searchTasksService.execute(getTasksRequest);
        logoutService.execute(new LogoutRequest());

        assertEquals(4, getTasksResponse.getTasks().size());
        assertEquals(LocalDateTime.of(2027,12,1,20,40),
                                                    getTasksResponse.getTasks().get(0).getEndDate());
        assertEquals(LocalDateTime.of(2023,12,30,8,10),
                                                    getTasksResponse.getTasks().get(1).getEndDate());
        assertEquals(LocalDateTime.of(2023,12,1,20,40),
                                                    getTasksResponse.getTasks().get(2).getEndDate());
        assertEquals(LocalDateTime.of(2023,11,12,9,5),
                                                    getTasksResponse.getTasks().get(3).getEndDate());
    }

    @Test
    public void searchUsersTasksAscending() {
        AddTaskRequest request;
        UserRegistrationRequest registrationRequest;
        SearchTasksRequest getTasksRequest;
        SearchTasksResponse getTasksResponse;
        Ordering order;

        registrationRequest= new UserRegistrationRequest("1111",
                "1111","a@b.c", false);
        userService.execute(registrationRequest);


        loginService.execute(new LoginRequest("1111","1111"));

        request = new AddTaskRequest("first task task", 0,
                LocalDateTime.of(2023,9,12,9,4),
                LocalDateTime.of(2023,11,12,9,5));
        taskService.execute(request);
        request = new AddTaskRequest("2nd task task", 2,
                LocalDateTime.of(2023,11,30,8,10),
                LocalDateTime.of(2023,12,1,20,40));
        taskService.execute(request);
        request = new AddTaskRequest("3rd task task task", 0,
                LocalDateTime.of(2023,7,30,8,10),
                LocalDateTime.of(2023,12,30,8,10));
        taskService.execute(request);
        request = new AddTaskRequest("check joga classes", 2,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2027,12,1,20,40));
        taskService.execute(request);

        logoutService.execute(new LogoutRequest());


        loginService.execute(new LoginRequest("1111","1111"));
        order = new Ordering("description", "ascending");
        getTasksRequest = new SearchTasksRequest("tas", order);
        getTasksResponse = searchTasksService.execute(getTasksRequest);
        logoutService.execute(new LogoutRequest());

        assertEquals(3, getTasksResponse.getTasks().size());
        assertEquals("2nd task task", getTasksResponse.getTasks().get(0).getDescription());
        assertEquals("3rd task task task", getTasksResponse.getTasks().get(1).getDescription());
        assertEquals("first task task", getTasksResponse.getTasks().get(2).getDescription());



        loginService.execute(new LoginRequest("1111","1111"));
        order = new Ordering("end date", "ascending");
        getTasksRequest = new SearchTasksRequest("20", order);
        getTasksResponse = searchTasksService.execute(getTasksRequest);
        logoutService.execute(new LogoutRequest());

        assertEquals(4, getTasksResponse.getTasks().size());
        assertEquals(LocalDateTime.of(2023,11,12,9,5),
                getTasksResponse.getTasks().get(0).getEndDate());
        assertEquals(LocalDateTime.of(2023,12,1,20,40),
                getTasksResponse.getTasks().get(1).getEndDate());
        assertEquals(LocalDateTime.of(2023,12,30,8,10),
                getTasksResponse.getTasks().get(2).getEndDate());
        assertEquals(LocalDateTime.of(2027,12,1,20,40),
                getTasksResponse.getTasks().get(3).getEndDate());
    }

    @Test
    public void searchTasksOrderingPaging() {
        AddTaskRequest request;
        UserRegistrationRequest registrationRequest;
        SearchTasksRequest getTasksRequest;
        SearchTasksResponse getTasksResponse;
        Ordering order;
        Paging pages;

        registrationRequest= new UserRegistrationRequest("1111",
                "1111","a@b.c", false);
        userService.execute(registrationRequest);


        loginService.execute(new LoginRequest("1111","1111"));

        request = new AddTaskRequest("first task task", 0,
                LocalDateTime.of(2023,9,12,9,4),
                LocalDateTime.of(2023,11,12,9,5));
        taskService.execute(request);
        request = new AddTaskRequest("2nd task task", 2,
                LocalDateTime.of(2023,11,30,8,10),
                LocalDateTime.of(2023,12,1,20,40));
        taskService.execute(request);
        request = new AddTaskRequest("3rd task task task", 0,
                LocalDateTime.of(2023,7,30,8,10),
                LocalDateTime.of(2023,12,30,8,10));
        taskService.execute(request);
        request = new AddTaskRequest("check joga classes.", 2,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2027,12,1,20,40));
        taskService.execute(request);
        request = new AddTaskRequest("check joga classes. restart", 2,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2028,10,1,20,40));
        taskService.execute(request);
        request = new AddTaskRequest("check joga classes. new restart", 2,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2029,10,1,20,40));
        taskService.execute(request);
        request = new AddTaskRequest("check joga classes. new new restart", 2,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2030,10,1,20,40));
        taskService.execute(request);

        logoutService.execute(new LogoutRequest());


        loginService.execute(new LoginRequest("1111","1111"));
        order = new Ordering("end date", "ascending");
        pages = new Paging(1, 3);
        getTasksRequest = new SearchTasksRequest("joga", order, pages);
        getTasksResponse = searchTasksService.execute(getTasksRequest);
        logoutService.execute(new LogoutRequest());

        assertEquals(3, getTasksResponse.getTasks().size());
        assertEquals("check joga classes.",
                        getTasksResponse.getTasks().get(0).getDescription());
        assertEquals("check joga classes. restart",
                        getTasksResponse.getTasks().get(1).getDescription());
        assertEquals("check joga classes. new restart",
                            getTasksResponse.getTasks().get(2).getDescription());
    }
}
