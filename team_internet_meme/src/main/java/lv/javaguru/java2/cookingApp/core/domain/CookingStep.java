package lv.javaguru.java2.cookingApp.core.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class CookingStep {
    private int stepOrder;
    private String stepDescription;

    @Override
    public String toString() {
        return "Step " + stepOrder + ":\n" + stepDescription + "\n";
    }
}
