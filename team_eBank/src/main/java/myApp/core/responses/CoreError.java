package myApp.core.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CoreError {

    private String field;
    private String message;
}
