package generalPackage.UIActions.usersOperationsUI;

import org.springframework.stereotype.Component;

@Component
public class ExitServiceUIAction implements UI_Menu {

    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
