package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.requests.ExitRequest;
import lv.javaguru.java2.tasksScheduler.responses.ExitResponse;

@DIComponent
public class ExitService {

    public ExitResponse execute(ExitRequest request) {
        return new ExitResponse();
    }
}
