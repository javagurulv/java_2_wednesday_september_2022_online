package lv.javaguru.java2.atmapp.usersOperationsUI;


import lv.javaguru.java2.atmapp.ATM_app;

public class MainMenuUIAction implements UI_Menu {

    @Override
    public void execute() {
        ATM_app.startApp();
    }
}
