package lv.javaguru.java2.rentapp.core.services;

import org.springframework.stereotype.Component;

@Component
public class ExitProgramService {

    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
