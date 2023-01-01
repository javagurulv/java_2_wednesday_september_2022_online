package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.Client;

import java.util.List;

@Getter
public class ExistedClientCheckResponse extends CoreResponse {

    private Client exClient;

    private String message;

    public ExistedClientCheckResponse(List<CoreError> errors) {
        super(errors);
    }

    public ExistedClientCheckResponse(Client exClient) {
        this.exClient = exClient;
    }

    public ExistedClientCheckResponse(String message) {
        this.message = message;
    }

    public boolean hasMessage() {
        return message != null && !message.isBlank();
    }
}
