package lv.javaguru.java2.eBooking.core.responses.client_response;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class SearchClientResponse extends CoreResponse {
    private List<CoreError> errors;

    public SearchClientResponse(List<CoreError> errors, List<Client> client) {
        super(errors);
        this.errors = errors;
    }

    @Override
    public List<CoreError> getErrors() {
        return errors;
    }
}
