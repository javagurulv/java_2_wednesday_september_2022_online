package generalPackage.adminOperationsUI;


import generalPackage.ATM_app;

public class AdminMainMenuUIAction implements AdminUIactions {
    @Override
    public void execute() {
        ATM_app.startApp();
    }
}
