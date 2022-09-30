package lv.javaguru.java2.esimpleforum.service;

import lv.javaguru.java2.esimpleforum.database.Database;

public class UserManagerService {

    private Database database;
    private boolean isLoggedIn;

    public UserManagerService(Database database) {
        this.database = database;
    }

    private void createNewUser(){

    }
}
