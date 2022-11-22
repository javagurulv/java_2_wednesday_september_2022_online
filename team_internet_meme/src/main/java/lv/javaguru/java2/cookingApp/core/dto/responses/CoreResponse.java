package lv.javaguru.java2.cookingApp.core.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
abstract class CoreResponse {

    private List<CoreError> errors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}
