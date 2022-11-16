package generalPackage.core.responses.usersResponses;

public class CoreErrorUsers {

    private String field;
    private  String message;

    public CoreErrorUsers(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
