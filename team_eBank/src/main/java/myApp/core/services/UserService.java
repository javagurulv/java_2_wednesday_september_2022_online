package myApp.core.services;


import myApp.core.database.DataBase;

public class UserService {
    private DataBase dataBase;
    private String personalCode;

    public UserService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public String logIn(String personalCode) {
        setPersonalCode(personalCode);
        return personalCode;
    }

    public void logOut() {
        if (this.personalCode != null) {
            this.personalCode = null;
        }
    }

    public String getPersonalCode() {
        return this.personalCode;
    }

   private void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

}
