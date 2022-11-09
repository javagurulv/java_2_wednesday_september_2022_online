package lv.javaguru.java2.cookingApp.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dishName", nullable = false)
    private String dishName;

    public Recipe() {
    }

    public Recipe(String dishName) {
        this.dishName = dishName;
    }

    @Override
    public String toString() {
        return "ID=" + id + " Name: " + dishName;
    }

}
