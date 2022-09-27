package lv.javaguru.java2.atmapp.adminServices;

import lv.javaguru.java2.atmapp.database.Database;

public class FindUserByIDServiсe {

    private Database database;

    public FindUserByIDServiсe(Database database) {
        this.database = database;
    }

public void execute (int userID){
database.findUserByID(userID);
}

}
