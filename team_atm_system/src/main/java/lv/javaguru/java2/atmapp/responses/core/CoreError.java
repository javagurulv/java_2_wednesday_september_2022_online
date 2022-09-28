package lv.javaguru.java2.atmapp.responses.core;


public class CoreError extends CoreResponse {

    private String field;

    private String message;


    public CoreError(String field, String message) {
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
