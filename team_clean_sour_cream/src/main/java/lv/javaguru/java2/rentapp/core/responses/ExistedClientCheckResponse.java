package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class ExistedClientCheckResponse extends CoreResponse {

    private Long exClientId;

    public ExistedClientCheckResponse(List<CoreError> errors) {
        super(errors);
    }

    public ExistedClientCheckResponse(Long exClientId) {
        this.exClientId = exClientId;
    }
}
