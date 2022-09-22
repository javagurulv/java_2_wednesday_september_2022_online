package myApp.core.requests;

import myApp.Roles;

public class RolesCheckRequest {

    private String role;
    public RolesCheckRequest(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
