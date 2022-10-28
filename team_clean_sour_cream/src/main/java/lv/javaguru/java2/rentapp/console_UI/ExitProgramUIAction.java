package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.services.ExitProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExitProgramUIAction implements UIAction {

    @Autowired
    ExitProgramService exitProgramService;

    @Override
    public void execute() {
        exitProgramService.execute();
    }
}
