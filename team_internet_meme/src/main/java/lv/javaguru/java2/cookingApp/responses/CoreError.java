package lv.javaguru.java2.cookingApp.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoreError {

    private String field;
    private String message;

}
