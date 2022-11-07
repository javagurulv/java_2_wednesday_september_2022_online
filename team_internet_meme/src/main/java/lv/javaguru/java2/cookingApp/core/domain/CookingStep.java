package lv.javaguru.java2.cookingApp.core.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CookingStep {
    private Long id;
    private int stepOrder;
    private String stepDescription;

    public CookingStep(int stepOrder, String stepDescription) {
        this.stepOrder = stepOrder;
        this.stepDescription = stepDescription;
    }

    @Override
    public String toString() {
        return "Step " + stepOrder + ":\n" + stepDescription + "\n";
    }
}
