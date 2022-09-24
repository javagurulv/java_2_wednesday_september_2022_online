package lv.javaguru.java2.rentapp.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
abstract class  CoreResponse {

    private List<CoreError> errors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}
