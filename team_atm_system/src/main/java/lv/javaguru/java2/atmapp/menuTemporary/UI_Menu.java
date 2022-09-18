package lv.javaguru.java2.atmapp.menuTemporary;

import lv.javaguru.java2.atmapp.menuTemporary.Actions.OperationMenu;


class UI_Menu {
    //    static private AuthorisationMethods authorisationMethods = new AuthorisationMethods();


    static private OperationMenu operationMenu = new OperationMenu();
    private AccountsTemp account;




    public static void main(String[] args) {
        //        authorisationMethods.userName();

        operationMenu.operationMenu();

    }
}
