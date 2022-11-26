package lv.javaguru.java2.cookingApp.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "ingredients")
@SecondaryTable(name = "recipes_to_ingredients")
public class Ingredient {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ingredient", nullable = false)
    private String name;
    @Column(name = "measurement", table = "recipes_to_ingredients")
    private String measurement;
    @Column(name = "amount", nullable = false, table = "recipes_to_ingredients")
    private Double amount;


    public Ingredient() {
    }

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


