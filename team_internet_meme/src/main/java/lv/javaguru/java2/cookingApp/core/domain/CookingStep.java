package lv.javaguru.java2.cookingApp.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "cooking_steps")
public class CookingStep {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "step_order", nullable = false)
    private int stepOrder;
    @Column(name = "instruction", nullable = false)
    private String stepDescription;
    @OneToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

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
