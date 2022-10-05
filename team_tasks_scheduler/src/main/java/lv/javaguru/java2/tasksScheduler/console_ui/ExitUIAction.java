package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.requests.ExitRequest;
import lv.javaguru.java2.tasksScheduler.responses.ExitResponse;
import lv.javaguru.java2.tasksScheduler.services.ExitService;

public class ExitUIAction implements UIAction {

    public ExitService exitService;

    public ExitUIAction(ExitService exitService) {
        this.exitService = exitService;
    }

    @Override
    public boolean execute() {
        ExitRequest request = new ExitRequest();
        ExitResponse response = exitService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        } else {
            System.out.println("Good bye!");
            System.exit(0);
            return true;
        }
    }
}
