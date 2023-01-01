package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.Client;

import java.util.List;

@Getter
public class AddClientResponse extends CoreResponse {

    private Long clientId;

    private String message;

    private Client newClient;

    public AddClientResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddClientResponse(String message) {
        this.message = message;
    }

    public AddClientResponse(Long clientId) {
        this.clientId = clientId;
    }
}
