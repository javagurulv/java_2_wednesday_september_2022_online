package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserInfoValidator {
    public List<CoreError> validate(UserRegistrationRequest request) {
        List<CoreError> errors = new ArrayList<>();
        //TODO
        return errors;
    }

    private Optional<CoreError> validateTitle(UserRegistrationRequest request) {
        return null;//TODO
    }

    private Optional<CoreError> validateAuthor(UserRegistrationRequest request) {
        return null;//TODO
    }
}
