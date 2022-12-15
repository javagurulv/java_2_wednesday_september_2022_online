package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class AddClientResponse extends CoreResponse {

    private Long clientId;

    public AddClientResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddClientResponse(Long clientId) {
        this.clientId = clientId;
    }
}
