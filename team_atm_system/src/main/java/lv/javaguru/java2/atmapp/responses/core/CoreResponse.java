package lv.javaguru.java2.atmapp.responses.core;

import lv.javaguru.java2.atmapp.responses.core.CoreError;

import java.util.List;

abstract class CoreResponse {

    private List<CoreError> errors;


    public CoreResponse() {
    }


    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }


    public List<CoreError> getErrors() {
        return errors;
    }


    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
