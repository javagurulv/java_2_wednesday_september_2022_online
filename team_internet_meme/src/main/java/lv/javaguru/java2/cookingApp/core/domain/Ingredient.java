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
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "ingredient")
    private String name;
    private String measurement;
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


