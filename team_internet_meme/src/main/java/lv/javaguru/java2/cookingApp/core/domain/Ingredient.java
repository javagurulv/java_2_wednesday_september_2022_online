package lv.javaguru.java2.cookingApp.core.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Ingredient {
    private Long id;
    private String name;
    private String measurement;
    private Double amount;

    public Ingredient(String name, String measurement, Double amount) {
        this.name = name;
        this.measurement = measurement;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return name + " " + amount + " " + measurement;
    }
}


