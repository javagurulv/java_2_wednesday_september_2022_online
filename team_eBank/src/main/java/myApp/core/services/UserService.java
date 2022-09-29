package myApp.core.services;

import myApp.core.database.DataBase;

public class UserService {
    private DataBase dataBase;
    private String personalCode;
    private String password;

    public UserService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public String logIn(String personalCode, String password) {
        setPersonalCode(personalCode);
        setPassword(password);
        return personalCode;
    }

    public void logOut() {
        if (this.personalCode != null && this.password != null) {
            this.personalCode = null;
            this.password = null;
        }
    }

    public String getPersonalCode() {
        return this.personalCode;
    }

   private void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
