package lv.javaguru.java2.tasksScheduler.core.services.menu_services;


import lv.javaguru.java2.tasksScheduler.core.requests.ExitRequest;
import org.springframework.stereotype.Component;

@Component
public class ExitService {

    public ExitResponse execute(ExitRequest request) {
        return new ExitResponse();
    }
}
