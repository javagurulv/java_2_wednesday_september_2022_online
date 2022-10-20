package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.requests.SearchTasksRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class SearchTasksValidator {
    public List<CoreError> validate(SearchTasksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }


}
