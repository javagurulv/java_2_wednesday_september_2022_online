package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.requests.ExitRequest;
import lv.javaguru.java2.tasksScheduler.responses.ExitResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.ExitService;

@DIComponent
public class ExitUIAction implements UIAction {

    @DIDependency public ExitService exitService;

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
