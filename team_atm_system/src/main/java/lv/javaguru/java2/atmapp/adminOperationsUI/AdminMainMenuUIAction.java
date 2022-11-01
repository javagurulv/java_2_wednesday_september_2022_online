package lv.javaguru.java2.atmapp.adminOperationsUI;


import lv.javaguru.java2.atmapp.ATM_app;

public class AdminMainMenuUIAction implements AdminUIactions {
    @Override
    public void execute() {
        ATM_app.startApp();
    }
}
