package myApp.core.responses;

public class RolesCheckResponse {

    private boolean isRoleAreAdmin;

    public RolesCheckResponse(boolean isRoleAreAdmin) {
        this.isRoleAreAdmin = isRoleAreAdmin;
    }

    public boolean isRoleAreAdmin() {
        return isRoleAreAdmin;
    }
}
