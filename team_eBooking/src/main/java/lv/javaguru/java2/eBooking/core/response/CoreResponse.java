package lv.javaguru.java2.eBooking.core.response;

import java.util.List;

abstract class CoreResponse {

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
