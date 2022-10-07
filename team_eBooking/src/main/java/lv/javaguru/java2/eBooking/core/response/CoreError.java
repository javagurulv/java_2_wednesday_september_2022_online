package lv.javaguru.java2.eBooking.core.response;

import lv.javaguru.java2.eBooking.core.service.ClientValidationResult;

public class CoreError {
    private String field;
    private ClientValidationResult message;

    public CoreError(String field, ClientValidationResult message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public ClientValidationResult getMessage() {
        return message;
    }
}
