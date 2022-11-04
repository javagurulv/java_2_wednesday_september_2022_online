package generalPackage.UIActions.adminOperationsUI;

import generalPackage.dependencyInjection.DIComponent;

@DIComponent
public class ExitAdminUIAction implements AdminUIactions {


    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
