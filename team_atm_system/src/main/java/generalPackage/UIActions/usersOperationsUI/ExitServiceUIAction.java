package generalPackage.UIActions.usersOperationsUI;

import generalPackage.dependencyInjection.DIComponent;

@DIComponent
public class ExitServiceUIAction implements UI_Menu {

    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
