package generalPackage.usersOperationsUI;


import generalPackage.ATM_app;

public class MainMenuUIAction implements UI_Menu {

    @Override
    public void execute() {
        ATM_app.startApp();
    }
}
