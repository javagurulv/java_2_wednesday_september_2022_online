package lv.javaguru.java2.rentapp.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class CoreError {

    private String field;
    private String message;

}
