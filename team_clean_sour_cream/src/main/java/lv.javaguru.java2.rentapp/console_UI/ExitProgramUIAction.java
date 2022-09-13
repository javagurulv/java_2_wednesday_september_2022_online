package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.services.ExitProgramService;

public class ExitProgramUIAction implements UIAction {

    ExitProgramService exitProgramService;

    public ExitProgramUIAction(ExitProgramService exitProgramService) {
        this.exitProgramService = exitProgramService;
    }

    @Override
    public void execute() {
        exitProgramService.execute();
    }
}
