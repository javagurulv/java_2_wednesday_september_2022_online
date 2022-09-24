package lv.javaguru.java2.atmapp.AdminOperations;

import lv.javaguru.java2.atmapp.Database.Database;

public class FindUserByIDServise {

    private Database database;

    public FindUserByIDServise(Database database) {
        this.database = database;
    }

public void execute (int userID){
database.findUserByID(userID);
}

}
