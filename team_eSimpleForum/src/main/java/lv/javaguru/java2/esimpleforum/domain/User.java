package lv.javaguru.java2.esimpleforum.domain;

import java.util.List;

public class User {
    int userId;
    String login;
    String password;
    List<Privilege> privileges;

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
