package lv.javaguru.java2.tasksScheduler.services.validators;


import lv.javaguru.java2.tasksScheduler.requests.SearchTasksRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchTasksValidator {
    public List<CoreError> validate(SearchTasksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }


}
