package lv.javaguru.java2.cookingApp.domain;

import lombok.*;

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
