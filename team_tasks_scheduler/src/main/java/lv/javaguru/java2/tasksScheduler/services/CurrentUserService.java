package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.domain.User;

public class CurrentUserService {

    private User currentUser;

    public CurrentUserService(User user) {
        this.currentUser = user;
    }

    public User execute() {
        return currentUser;
    }
}
