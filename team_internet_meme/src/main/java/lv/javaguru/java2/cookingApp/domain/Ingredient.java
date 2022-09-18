package lv.javaguru.java2.cookingApp.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor

public class Ingredient {
    private String name;
    private String measurement;
    private double amount;

    @Override
    public String toString() {
        return name + " " + amount + " " + measurement;
    }
}


