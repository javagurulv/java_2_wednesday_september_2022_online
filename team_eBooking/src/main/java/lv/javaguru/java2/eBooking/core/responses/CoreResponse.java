package lv.javaguru.java2.eBooking.core.responses;

import java.util.List;

public abstract class   CoreResponse {

    private List<CoreError> errors;

    public CoreResponse(){

    }
    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public boolean hasError() {
        return errors != null && !errors.isEmpty() ;
    }

}
