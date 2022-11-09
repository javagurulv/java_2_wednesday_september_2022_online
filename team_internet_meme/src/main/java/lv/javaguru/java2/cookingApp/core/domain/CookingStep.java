package lv.javaguru.java2.cookingApp.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "cooking_steps")
public class CookingStep {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "step_order", nullable = false)
    private int stepOrder;
    @Column(name = "instruction", nullable = false)
    private String stepDescription;

    public CookingStep() {
    }

    public CookingStep(int stepOrder, String stepDescription) {
        this.stepOrder = stepOrder;
        this.stepDescription = stepDescription;
    }

    @Override
    public String toString() {
        return "Step " + stepOrder + ":\n" + stepDescription + "\n";
    }
}
