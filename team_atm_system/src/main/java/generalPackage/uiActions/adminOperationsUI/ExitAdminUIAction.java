package generalPackage.uiActions.adminOperationsUI;

import org.springframework.stereotype.Component;

@Component
public class ExitAdminUIAction implements AdminUIactions {


    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
