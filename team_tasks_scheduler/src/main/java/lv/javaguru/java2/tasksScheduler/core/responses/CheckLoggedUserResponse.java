package lv.javaguru.java2.tasksScheduler.core.responses;

public class CheckLoggedUserResponse extends  CoreResponse {

    private boolean isUserLoggedIn;

    public CheckLoggedUserResponse(boolean isUserLoggedIn) {
        this.isUserLoggedIn = isUserLoggedIn;
    }

    public boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        isUserLoggedIn = userLoggedIn;
    }
}
