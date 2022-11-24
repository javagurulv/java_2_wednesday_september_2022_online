package apartment.core.services;

public class UserService {

    private String userLogin;

    public void login(String userLogin){
        this.userLogin = userLogin;
    }

    public void logOut(){
        this.userLogin = null;
    }

    public String getCurrentUser(){
        return this.userLogin;
    }

}
