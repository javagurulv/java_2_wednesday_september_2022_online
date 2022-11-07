package lv.javaguru.java2.cookingApp.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Recipe {
    private Long id;
    private String dishName;

    public Recipe(String dishName) {
        this.dishName = dishName;
    }

    @Override
    public String toString() {
        return "ID=" + id + " Name: " + dishName;
    }

}
